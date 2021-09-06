package com.cafe.managerassignment.model;

import com.cafe.managerassignment.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "user")
@Data
public class User extends AbstractBaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Email
    @NotEmpty
    @Column(unique=true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user", orphanRemoval = true)
    @JoinColumn(name = "Order_id")
    private Set<Order> orders = new HashSet<> (); //to not get NullPointer on Collections.unmidifiable()

    @Enumerated(EnumType.STRING)
//    @NotNull //commented to have possibility to set in controller
    @Column(length = 8, nullable = false)
    private ApplicationUserRole role;



    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({ "createdBy"})
    private Set<Table> createdTables = new HashSet<>();

    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnoreProperties({"assignedTo"})
    private Set<Table> assignedTables = new HashSet<>();

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public User() {
    }
    @JsonIgnore
    public boolean isManager(){
        return role.equals(ApplicationUserRole.MANAGER);
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public Set<Table> getCreatedTables() {
//        return Collections.unmodifiableList(createdTables);
        return Collections.unmodifiableSet(createdTables);
    }

    public void addTable(Table table){
        table.setCreatedBy(this);
        createdTables.add(table);
    }

    public Set<Table> getAssignedTables() {
        return Collections.unmodifiableSet(assignedTables);
    }
}
