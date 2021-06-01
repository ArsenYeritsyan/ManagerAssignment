package com.cafe.managerassignment.model.restmodel;

import lombok.*;

import java.io.Serializable;


@Data
public class UserResponseModel implements Serializable {
    private Long id;
    private String username;
    private String password;

    public UserResponseModel(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponseModel() {

    }
}
