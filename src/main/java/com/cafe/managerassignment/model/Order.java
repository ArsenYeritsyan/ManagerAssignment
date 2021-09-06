package com.cafe.managerassignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name="Order")
@Data
public class Order extends AbstractBaseEntity {

    public Order() {
    }

    public Order(
            Table table,
            OrderStatus status
    ) {
        this.table = table;
        this.status = status;
    }
    @ManyToOne()
    @JsonIgnoreProperties({"orders"})
    private User user;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "Order")
    private Set<ProductInOrder> products = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 4, nullable = false)
    private OrderStatus status;


    @OneToMany(mappedBy = "Order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductInOrder> productInOrders = new HashSet<>();


    @JsonIgnore
    public boolean isOpen(){
        return status == OrderStatus.OPEN;
    }
}
