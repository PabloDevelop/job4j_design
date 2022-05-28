package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**Принимает на вход текущий размер контейнера.
     * Увеличивает контейнера копируя его в новый
     * с вместимостью = (текущая + 1) * 2
     * @param containerLength
     */
    private void grow(int containerLength) {
        container = Arrays.copyOf(container, container.length * 2);
    }

    /**Принимает на вход объект.
     * Проверяет, не равен ли размер массива текущему размер контейнера.
     * Если равен, то вызывает метод grow() для размера контейнера.
     * Присваивает элементу массива по индексу значение объекта.
     * Увеличивает счетчик изменений массива на 1.
     * @param value
     */
    @Override
    public void add(T value) {
      if (size >= container.length) {
          grow(container.length + 1);
      }
      container[size++] = value;
      modCount++;
      }

    /**Принимает на вход объект и индекс.
     * Присваивает переменной removedValue значение объекта по индексу.
     * Присваивает элементу массива по индексу значение объекта.
     * Увеличивает счетчик изменений массива на 1.
     * @param index входящий индекс
     * @param newValue входящий объект
     * @return заменяемое значение элемента
     */
    @Override
    public T set(int index, T newValue) {
        T removedValue = get(index);
        container[index] = newValue;
        modCount++;
        return removedValue;
    }

    /**Принимает на вход индекс.
     * Присваивает переменной removedValue значение объекта по индексу.
     * Уменьшает размер массива на 1.
     * Проверяет, что значение входящего индекса меньше индекса последнего элемента.
     * Если true, то элементы копируются на один индекс влево.
     * Последнему элементу по индексу массива присваивается null.
     * Увеличивает счетчик изменений массива на 1.
     * @param index входящий индекс
     * @return возвращает удаляемый объект
     */
    @Override
    public T remove(int index) {
        T removedValue = get(index);
        size--;
        if (size > index) {
            System.arraycopy(container, index + 1, container, index, size - index);
        }
        container[size] = null;
        modCount++;
        return removedValue;
    }

    /**Проверяет, находится ли индекс в границах размера массива,
     * если не находится кидает IndexOutOfBoundsException.
     * Возвращает элемент массива по входящему индексу
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
                return cursor != size;
            }

            /**Переменная i служит индексом для текущего элемента.
             * Проверяет, не равен ли текущий индекс размеру массива,
             * если равен, то выкидывает NoSuchElementException.
             * Присваивает курсору значение текущего индекса + 1.
             * Возвращает элемент по текущему индексу.
             * @return элемент массива с текущим индексом
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                cursor += 1;
                return container[cursor - 1];
            }
        };
    }
}