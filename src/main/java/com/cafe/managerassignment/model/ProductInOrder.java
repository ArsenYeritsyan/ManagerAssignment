package com.cafe.managerassignment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "products_in_order")
public class ProductInOrder extends AbstractBaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductInOrderStatus status;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne
    User creator;

    @ManyToOne
    @MapsId
    private Order order_id;
    private Integer quantity;


}
