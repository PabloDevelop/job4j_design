package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    /**метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает.
     * @param model
     */
    @Override
    public void add(T model) {
        T t = storage.get(model.getId());
        if (t == null) {
            storage.put(model.getId(), model);
        }
    }

    /**метод выполняет замену объекта по id,
     * на объект который передается в параметрах метода
     * @param id
     * @param model
     * @return возвращает true, если замена выполнена успешно
     */
    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
            return true;
        } else {
            return false;
        }
    }

    /**метод удаляет пару ключ-значение по id, который передается в метод
     * @param id
     * @return возвращает true, если удаление выполнено успешно
     */
    @Override
    public boolean delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        } else {
            return false;
        }
    }

    /**метод возвращает объект по ключу, который равен id,
     * передаваемый в качестве параметра метода
     * @param id
     * @return возвращает null если по такому ключу в Map еще нет пары ключ-значение
     */
    @Override
    public T findById(String id) {
        if (storage.containsKey(id)) {
            return storage.get(id);
        } else {
            return null;
        }
    }
}