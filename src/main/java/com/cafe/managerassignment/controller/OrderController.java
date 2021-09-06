package com.cafe.managerassignment.controller;

import com.cafe.managerassignment.model.Order;
import com.cafe.managerassignment.model.OrderStatus;
import com.cafe.managerassignment.model.Table;
import com.cafe.managerassignment.model.restmodel.UserResponseModel;
import com.cafe.managerassignment.repo.OrderRepository;
import com.cafe.managerassignment.service.RoleUserService;
import com.cafe.managerassignment.service.TableService;
import com.cafe.managerassignment.service.UserService;
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
    private RoleUserService roleService;
    private UserService userRepo;
    private TableService tableRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, RoleUserService roleService,
                           UserService userRepo, TableService tableRepo) {
        this.orderRepo = orderRepo;
        this.roleService = roleService;
        this.userRepo = userRepo;
        this.tableRepo = tableRepo;
    }

    @PostMapping()
    public ResponseEntity creatOrder(
            @Validated @RequestBody Order order,
            @RequestParam("waiter_id") Long waiterId,
            @RequestParam("table_id") Long tableId) {
        Optional<UserResponseModel> waiterOptional = Optional.ofNullable (userRepo.findById (waiterId));
        roleService.mustBeWaiter (waiterOptional);
        Optional<Table> tableOptional = Optional.ofNullable (tableRepo.findById (tableId));
        Table table;
        table = tableOptional.get ();
       // Set<Order> existingOrders = table.getOrders ();
      // boolean hasOpen = existingOrders.stream ().anyMatch (Order -> Order.getStatus().equals (OrderStatus.OPEN));
        order.setTable (table);
        Order savedOrder = orderRepo.save (order);
        return ResponseEntity.status (HttpStatus.CREATED).body (savedOrder);
    }

}
