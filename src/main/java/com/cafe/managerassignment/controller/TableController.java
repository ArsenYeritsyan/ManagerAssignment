package com.cafe.managerassignment.controller;

import com.cafe.managerassignment.model.Table;
import com.cafe.managerassignment.model.User;
import com.cafe.managerassignment.repo.TableRepository;
import com.cafe.managerassignment.repo.UserRepository;
import com.cafe.managerassignment.sevice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
public class TableController {
    private UserRepository userRepo;
    private TableRepository tableRepo;
    private RoleService roleService;

    @Autowired
    public TableController(UserRepository userRepo, TableRepository tableRepo, RoleService roleService) {
        this.userRepo = userRepo;
        this.tableRepo = tableRepo;
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<Table> createTable(@Validated @RequestBody Table table, @RequestParam("manager_id") Long id) {
        Optional<User> userOptional = userRepo.findById(id);
        ResponseEntity badResponse = roleService.mustBeManager(userOptional);
        if (badResponse != null) {
            return badResponse;
        }
        User user = userOptional.get();

        table.setWaiter(user);
        Table createdTable = tableRepo.save(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTable);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Table>> getAllTables() {
        Iterable<Table> allTables = tableRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allTables);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Table> assignToWaiter(
            @PathVariable Long id,
            @RequestParam("manager_id") Long managerId,
            @RequestParam("waiter_id") Long waiterId) {
        //--validate---
        ResponseEntity badManagerResponse = roleService.mustBeManager(managerId);
        if (badManagerResponse != null) {
            return badManagerResponse;
        }
        Optional<User> waiterOptional = userRepo.findById(waiterId);
        ResponseEntity badWaiterResponse = roleService.mustBeWaiter(waiterOptional);
        if (badWaiterResponse != null) {
            return badWaiterResponse;
        }

        Optional<Table> tableOptional = tableRepo.findById(id);
        if (tableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //------
        Table table = tableOptional.get();
        User waiter = waiterOptional.get();
        table.setWaiter(waiter);
        table.setAssigned(true);
        Table savedTable = tableRepo.save(table);
        return ResponseEntity.status(HttpStatus.OK).body(savedTable);
    }
}
