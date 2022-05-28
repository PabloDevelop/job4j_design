package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;

    private int modCount = 0;

    transient Node<E> head;

    transient Node<E> tail;

    /**Принимает на вход значение.
     * Если в списке отсутствуют ноды, то присваивает head и tail
     * ссылки на новую ноду.
     * Если в списке есть ноды, то через переменную tail перебирает их по очереди
     * в цикле while пока next не будет null.
     * Затем присваивает tail ссылку на новую ноду.
     * Инкрементирует size.
     * Инкрементирует modCount.
     * @param value
     */
    @Override
    public void add(E value) {
        final Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            modCount++;
            return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
        size++;
        modCount++;
    }

    /**Принимает на вход индекс.
     * Проверяет находится ли индекс в пределах размера списка.
     * Сравнивая в цикле while пока count != index
     * перебирает подряд ноды.
     * @param index
     * @return значение в ноде по индексу
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        int count = 0;
        while (count != index) {
            current = current.next;
            count++;
        }
        return current.item;
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
            Node<E> cursor = head;
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

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}