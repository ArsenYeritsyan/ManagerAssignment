package com.cafe.managerassignment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "products_in_order")
public class ProductInOrder extends AbstractBaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InOrderStatus status;
    @Column(nullable = false)
    private Double amount;
    @ManyToOne
    private Product product;

    @ManyToOne
    User creator;
    @ManyToOne
    @MapsId
    private Order order_id;
    private Integer quantity;


}
