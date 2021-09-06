package com.cafe.managerassignment.repo;

import com.cafe.managerassignment.model.Order;
import com.cafe.managerassignment.model.OrderStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public abstract Order save(@NotNull Order entity);
    public abstract Optional<Order> findById(Long entityId);
    public abstract Order update(Order entity);
    public abstract Order updateById(Order entity, Long entityId);
    public abstract void delete(Order entity);
    public abstract void deleteById(Long entityId);
    List<Order> findAll();
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByStatusNot(OrderStatus status);
    List<Order> findTopByCreatedBetween(Date created, LocalDate created2);
}
