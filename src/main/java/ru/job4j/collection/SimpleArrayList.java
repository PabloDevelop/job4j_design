package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**Принимает на вход объект.
     * Проверяет, не равен ли размер массива его текущей вместимости.
     * Если равен, то увеличивает массив копируя его в новый
     * с вместимостью = текущая * 2 (аналог метода grow()).
     * Присваивает элементу массива по индексу значение объекта.
     * Увеличивает счетчик изменений массива на 1.
     * @param value
     */
    @Override
    public void add(T value) {
      if (size >= container.length) {
          container = Arrays.copyOf(container, container.length * 2);
      }
      container[size++] = value;
      modCount++;
      }

    /**Принимает на вход объект и индекс.
     * Проверяет, находится ли индекс в границах размера массива,
     * если не находится, то кидает IndexOutOfBoundsException.
     * Если true, то присвает элементу массива по индексу значение объекта.
     * Увеличивает счетчик изменений массива на 1.
     * @param index входящий индекс
     * @param newValue входящий объект
     * @return заменяемое значение элемента
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T removedValue = container[index];
        container[index] = newValue;
        modCount++;
        return removedValue;
    }

    /**Принимает на вход индекс.
     * Проверяет, находится ли индекс в границах размера массива,
     * если не находится кидает IndexOutOfBoundsException.
     * Проверяет, что значение входящего индекса меньше индекса последнего элемента.
     * Если true, то элементы копируются на один индекс влево.
     * Последнему элементу по индексу массива присваивается null.
     * Уменьшает размер массива на 1.
     * Увеличивает счетчик изменений массива на 1.
     * @param index входящий индекс
     * @return возвращает удаляемый объект
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedValue = container[index];
        if ((size - 1) > index) {
            System.arraycopy(container, index + 1, container, index, size - 1 - index);
        }
        container[size - 1] = null;
        size -= 1;
        modCount++;
        return removedValue;
    }

    /**Возвращает элемент массива по входящему индексу
     * @param index входящий индекс
     * @return элемент по индексу
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**Возвращает размер массива (количество хранимых элементов).
     * @return размер массива
     */
    @Override
    public int size() {
        return size;
    }

    /**Во вложенном итераторе есть переменные:
     * cursor - индекс следующего элемента в массиве,
     * expectedModCount - внутренняя константа равная счетчику
     * изменений массива.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor;
            int expectedModCount = modCount;

            /**Возвращает true, если курсор != размеру массива.
             * @return true или false
             */
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            /**Переменная i служит индексом для текущего элемента.
             * Проверяет, не равен ли текущий индекс размеру массива,
             * если равен, то выкидывает NoSuchElementException.
             * Сравнивает внутреннюю константу изменений с счетчиком,
             * если они не равны (массив изменяли во время работы итератора),
             * то выкидывает ConcurrentModificationException.
             * Присваивает курсору значение текущего индекса + 1.
             * Возвращает элемент по текущему индексу.
             * @return элемент массива с текущим индексом
             */
            @Override
            public T next() {
                int i = cursor;
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                cursor = i + 1;
                return container[i];
            }
        };
    }
}