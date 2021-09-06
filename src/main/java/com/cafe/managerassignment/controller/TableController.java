package com.cafe.managerassignment.controller;

import com.cafe.managerassignment.model.Table;
import com.cafe.managerassignment.model.User;
import com.cafe.managerassignment.repo.TableRepository;
import com.cafe.managerassignment.repo.UserRepository;
import com.cafe.managerassignment.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
public class TableController {
    private UserRepository userRepo;
    private TableRepository tableRepo;
    private RoleUserService roleService;

    @Autowired
    public TableController(UserRepository userRepo, TableRepository tableRepo, RoleUserService roleService) {
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

        table.setAssignedTo(user);
        Table createdTable = tableRepo.save(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTable);
    }

    @GetMapping()
    public ResponseEntity getAllTables() {
        List allTables = tableRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allTables);
    }
}
