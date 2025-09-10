package dev.tiagosilva.business;

import dev.tiagosilva.controller.Controller;

import java.util.List;

abstract class BusinessHandler<T extends BusinessHandler<T>> {
    abstract public List<T> get();

    abstract public T get(Long id);

    abstract public T create(T obj);

    abstract public T update(Long id, T obj);

    abstract boolean delete(Long id);
}
