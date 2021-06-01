package com.cafe.managerassignment.repo;

import java.util.Collection;
import java.util.Optional;

public interface AbstractBaseRepository<T> {
    public Optional<T> save(T domain);

    public Iterable<T> save(Collection<T> domains);

    public void delete(T domain);

    public Optional<T> findById(String id);

    public Iterable<T> findAll();
}
