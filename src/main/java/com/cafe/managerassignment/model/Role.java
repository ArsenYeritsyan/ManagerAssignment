package com.cafe.managerassignment.model;

import com.cafe.managerassignment.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "`role_name`", length = 7, nullable = false)
    ApplicationUserRole roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    List<User> users;

    public Role() {
    }
}
