package com.cafe.managerassignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "orders")
@Data
public class Order extends AbstractBaseEntity {


    @OneToOne
    @MapsId
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private Set<ProductInOrder> products = new HashSet<>();

    @NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 9, nullable = false)
    private OrderStatus status;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductInOrder> productInOrders = new HashSet<>();

    public Order() {
    }

    public Order(
            Table table,
            OrderStatus status
    ) {
        this.table = table;
        this.status = status;
    }

}
