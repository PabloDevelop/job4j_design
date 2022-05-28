package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;

    private int modCount = 0;

    transient Node<E> first;

    transient Node<E> last;

    /**Принимает на вход значение.
     * Присваивает узлу l значение last.
     * Создает новый узел newNode с ссылками l и null (next)
     * помещая в него значение.
     * Устанавливает ссылку last на узел newNode.
     * Если ссылка l = null, то устанавливает ссылку first
     * на newNode, иначе устанавливает ссылку next на newNode.
     * Инкрементирует size.
     * Инкрементирует modCount.
     * @param value
     */
    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**Принимает на вход индекс Node.
     * Проверяет находится ли индекс в пределах размера листа.
     * Присваивает узлу f ссылку на узел first.
     * Проходами в цикле до индекса перерибает узлы и
     * присваивает узлу f значение ссылки по индексу.
     * @param index
     * @return элемент списка по индексу узла
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> f = first;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        return f.item;
    }

    /**Во вложенном итераторе есть переменные:
     * cursor - присвоена ссылка на узел первого элемента в списке,
     * expectedModCount - внутренняя константа равная счетчику
     * изменений листа.
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> cursor = first;
            int expectedModCount = modCount;

            /**Возвращает true, если курсор != null.
             * Сравнивает внутреннюю константу изменений с счетчиком,
             * если они не равны (массив изменяли во время работы итератора),
             * то выкидывает ConcurrentModificationException.
             * @return true или false
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            /**Если hasNext() false, то выкидывает NoSuchElementException.
             * Присваивает перепенной узлу itemReturn значение текущего cursor.
             * Присваивает cursor значение ссылки на следующий узел.
             * @return элемент списка по узлу itemReturn
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> itemReturn = cursor;
                cursor = cursor.next;
                return itemReturn.item;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}