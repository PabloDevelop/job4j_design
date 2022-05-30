package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**Удаляет первое значение из коллекции.
     * @returnм возвращает первое значение из коллекции
     */
    public T poll() {
        if (isEmpty(out)) {
            if (isEmpty(in)) {
                throw new NoSuchElementException();
            }
            while (!isEmpty(in)) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }


    /**Помещает значение в конец
     * @param value
     */
    public void push(T value) {
        in.push(value);
    }

    public boolean isEmpty(SimpleStack<T> stack) {
        return stack.getSize() == 0;
    }
}