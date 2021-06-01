package com.cafe.managerassignment.sevice;

import com.cafe.managerassignment.model.User;
import com.cafe.managerassignment.repo.UserRepository;
import com.cafe.managerassignment.security.ApplicationUserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private UserRepository userRepo;

    public RoleService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity mustBeManager(Long id) {
        Optional<User> managerOptional = userRepo.findById(id);
        return mustBeManager(managerOptional);
    }

    public ResponseEntity mustBeManager(Optional<User> user) {
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!user.get().getRole().equals(ApplicationUserRole.USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return null;
    }

    public ResponseEntity mustBeWaiter(Long id) {
        Optional<User> waiterOptional = userRepo.findById(id);
        return mustBeWaiter(waiterOptional);
    }

    public ResponseEntity mustBeWaiter(Optional<User> user) {
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (user.get().getRole().equals(ApplicationUserRole.USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return null;
    }
}
