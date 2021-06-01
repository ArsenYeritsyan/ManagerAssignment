package com.cafe.managerassignment.repo;

import com.cafe.managerassignment.model.Order;
import com.cafe.managerassignment.model.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByStatusNot(OrderStatus status);
}
