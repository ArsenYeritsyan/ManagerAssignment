package com.cafe.managerassignment.controller;

import com.cafe.managerassignment.model.Order;
import com.cafe.managerassignment.model.OrderStatus;
import com.cafe.managerassignment.model.Table;
import com.cafe.managerassignment.model.User;
import com.cafe.managerassignment.repo.OrderRepository;
import com.cafe.managerassignment.repo.TableRepository;
import com.cafe.managerassignment.repo.UserRepository;
import com.cafe.managerassignment.sevice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderRepository orderRepo;
    private RoleService roleService;
    private UserRepository userRepo;
    private TableRepository tableRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, RoleService roleService,
                           UserRepository userRepo, TableRepository tableRepo) {
        this.orderRepo = orderRepo;
        this.roleService = roleService;
        this.userRepo = userRepo;
        this.tableRepo = tableRepo;
    }

    @PostMapping()
    public ResponseEntity<Order> creatOrder(
            @Validated @RequestBody Order order,
            @RequestParam("waiter_id") Long waiterId,
            @RequestParam("table_id") Long tableId) {

        //---- Validate ----
        Optional<User> waiterOptional = userRepo.findById(waiterId);
        ResponseEntity badWaiterResponse = roleService.mustBeWaiter(waiterOptional);
        if (badWaiterResponse != null) {
            return badWaiterResponse;
        }

        Optional<Table> tableOptional = tableRepo.findById(tableId);
        if (tableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Table table = tableOptional.get();
        Set<Order> existingOrders = table.getOrders();
        boolean hasOpen = existingOrders.stream().anyMatch(Order -> Order.getStatus().equals(OrderStatus.OPEN));
        if (hasOpen) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        order.setTable(table);
        Order savedOrder = orderRepo.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
}
