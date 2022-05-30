package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private int size = 0;

    /**Удаляет значение из первого элемента коллекции.
     * @return удаляемое значение
     */
    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    /**Помещает значение в первый элемент коллекции.
     * @param value
     */
    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    public int getSize() {
        return size;
    }
}