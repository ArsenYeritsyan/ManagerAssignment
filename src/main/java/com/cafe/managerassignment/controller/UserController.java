package com.cafe.managerassignment.controller;

import com.cafe.managerassignment.model.Table;
import com.cafe.managerassignment.model.User;
import com.cafe.managerassignment.repo.UserRepository;
import com.cafe.managerassignment.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@PreAuthorize("hasRole('MANAGER')")
@RequestMapping("/api/users")
public class UserController {
    private UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping()
    public ResponseEntity<User> createUserByManager(@Validated @RequestBody User user, @RequestParam("manager_id") Long id) {
        Optional<User> managerOptional = userRepo.findById(id);

        if (managerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        user.setRole(ApplicationUserRole.USER);
        User savedUser = (User) userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User expectedUser = userRepo.findByUsername(user.getUsername()).get();
        if (expectedUser == null || ! expectedUser.getPassword().equals(user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(expectedUser);
    }

    @GetMapping()
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> allUsers = userRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping("{id}/tables}")
    public ResponseEntity<Iterable<Table>> getTables(@PathVariable Long id) {
        Optional<User> waiterOptional = userRepo.findById(id);
        if (waiterOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (waiterOptional.get().getRole().equals(ApplicationUserRole.MANAGER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User waiter = waiterOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(waiter.getAssignedTables());
    }
}
