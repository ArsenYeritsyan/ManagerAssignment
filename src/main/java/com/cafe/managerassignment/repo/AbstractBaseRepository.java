package com.cafe.managerassignment.repo;

import com.cafe.managerassignment.model.AbstractBaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface AbstractBaseRepository<T extends AbstractBaseEntity, Long> extends JpaRepository<T,Long> {
    public abstract T save(T entity);
    public abstract List<T> findAll(); // you might want a generic Collection if u prefer

    public abstract Optional<T> findById(java.lang.Long entityId);
    public abstract T update(T entity);
    public abstract T updateById(T entity, java.lang.Long entityId);
    public abstract void delete(T entity);
    public abstract void deleteById(java.lang.Long entityId);

    public Iterable<T> save(Collection<T> domains);

    public Optional<T> findById(String id);
}
