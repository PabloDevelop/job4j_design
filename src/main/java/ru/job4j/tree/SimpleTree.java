package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /** Должен находить узел по значению parent
     * и добавлять в него дочерний узел со значением child.
     * В этом методе нужно проверить,
     * что значения child еще нет в дереве, а parent есть.
     * Если child есть, то метод должен вернуть false.
     * @param parent
     * @param child
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        return findBy(parent).isPresent()
                && findBy(child).isEmpty()
                && findBy(parent).get().children.add(new Node<>(child));
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}