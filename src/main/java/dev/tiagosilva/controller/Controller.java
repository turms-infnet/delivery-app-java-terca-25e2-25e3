package dev.tiagosilva.controller;

import java.util.List;

public abstract class Controller<T extends Controller<T>> {
    abstract public List<T> get();

    abstract public T get(Long id);

    abstract public T create(T obj);

    abstract public T update(Long id, T obj);

    abstract public boolean delete(Long id);
}
