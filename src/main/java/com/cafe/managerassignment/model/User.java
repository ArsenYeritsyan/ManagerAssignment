package com.cafe.managerassignment.model;

import com.cafe.managerassignment.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity(name = "user")
@Data
public class User extends AbstractBaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @NotNull
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name")}
    )
    private ApplicationUserRole role;
    @Column(nullable = false, unique = true)
    @NotNull
    private String username;

    @OneToMany
    @MapsId
    private List<Table> assignedTables;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public User() {

    }
}
