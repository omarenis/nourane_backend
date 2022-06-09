package com.example.backend.services.interfaces;

import java.util.List;

public interface AbstractGenericServiceInterface<T> {
    public List<T> list();

    public T create(T entity);

    public T findById(Long id);

    public T update(Long id, T entity);

    public void delete(Long id);
}
