package com.cafe.managerassignment.sevice;

import com.cafe.managerassignment.model.*;
import com.cafe.managerassignment.repo.OrderRepository;
import com.cafe.managerassignment.repo.ProductInOrderRepository;
import com.cafe.managerassignment.repo.TableRepository;
import com.cafe.managerassignment.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductInOrderRepository productInOrderRepository;


    public OrderService(OrderRepository orderRepository, ProductInOrderRepository productInOrderRepository) {
        this.orderRepository = orderRepository;
        this.productInOrderRepository = productInOrderRepository;
    }

    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<Order> sortedOrders = orders.stream().sorted().collect(Collectors.toList());
        return sortedOrders;
    }

    public Order findById(String id) {
        return orderRepository.findById(Long.parseLong(id)).orElseThrow(IllegalStateException::new);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void deleteById(String id) {
        long orderId = Long.parseLong(id);
        orderRepository.deleteById(orderId);
    }

    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findByStatus(orderStatus);

        return orders;
    }

    public List<ProductInOrder> findProductsInOrder(Order order) {
        return productInOrderRepository.findByOrder_id(order.getId());
    }

    public void saveProductInOrder(ProductInOrder productInOrder) {
        productInOrderRepository.save(productInOrder);
    }

    public void deleteProductInOrder(String productInOrderId) {
        long id = Long.parseLong(productInOrderId);
        productInOrderRepository.deleteById(id);
    }

    public List<Order> findActiveOrders(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findByStatusNot(orderStatus);

        return orders;
    }
}
