package com.cafe.managerassignment.model;

import com.cafe.managerassignment.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Role extends AbstractBaseEntity {
    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "`role_name`", length = 7, nullable = false)
    ApplicationUserRole roleName;

    @ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    List<User> users;

    public Role() {
    }
}
