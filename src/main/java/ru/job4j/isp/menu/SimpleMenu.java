package ru.job4j.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Каркас меню.
 * <br>ItemInfo. Он служит для того,
 * чтобы скомпоновать пункт меню и номер пункта (1.1., 1.1.1. и т.д.).
 * <br>SimpleMenuItem. Это реализация MenuItem для SimpleMenu.
 * <br></be>DFSIterator. Это итератор, основанный на поиске в глубину.
 * Но немного модифицированный, поскольку нам удобно читать
 * пункты меню сверху-вниз и слева-направо.
 */
public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    /**
     * Реализуйте на основе метода {@link SimpleMenu#findItem(String)}.
     *
     * @param parentName
     * @param childName
     * @param actionDelegate
     * @return
     */
    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        SimpleMenuItem simpleMenuItem = new SimpleMenuItem(childName, actionDelegate);
        boolean rsl;
        if (findItem(childName).isPresent()) {
            rsl = false;
        } else if (Objects.equals(parentName, ROOT)) {
            rsl = rootElements.add(simpleMenuItem);
        } else {
            rsl = findItem(parentName)
                    .map(itemInfo -> itemInfo.menuItem.getChildren().add(simpleMenuItem))
                    .orElse(false);
        }
        return rsl;
    }

    /**
     * Реализуйте на основе метода {@link SimpleMenu#findItem(String)}.
     *
     * @param itemName
     * @return
     */
    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(i -> new MenuItemInfo(
                i.menuItem.getName(), i.menuItem.getChildren().stream()
                .map(MenuItem::getName).collect(Collectors.toList()),
                i.menuItem.getActionDelegate(), i.number
        ));
    }

    /**
     * Также на основе существующего итератора реализуйте метод iterator()
     *
     * @return
     */
    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator() {
            DFSIterator dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    /**
     * Реализуйте поиск в методе findItem() на основе {@link DFSIterator}.
     *
     * @param name
     * @return
     */
    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIterator = new DFSIterator();
        Optional<ItemInfo> itemInfo = Optional.empty();
        ItemInfo rsl;
        while (dfsIterator.hasNext()) {
            rsl = dfsIterator.next();
            if (name.equals(rsl.menuItem.getName())) {
                itemInfo = Optional.of(rsl);
                break;
            }
        }
        return itemInfo;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}