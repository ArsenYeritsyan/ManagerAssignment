package com.cafe.managerassignment.model.restmodel;

import com.cafe.managerassignment.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;


@Data
public class UserResponseModel implements Serializable {
    private Long id;
    private String username;
    private String password;
    private ApplicationUserRole role;

    public UserResponseModel(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponseModel() {

    }

    public boolean isManager(){
        return role.equals(ApplicationUserRole.MANAGER);
    }
}
