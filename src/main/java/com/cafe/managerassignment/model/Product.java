package com.cafe.managerassignment.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "products")
@Data
public class Product extends AbstractBaseEntity {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Double price;

    @OneToMany(mappedBy = "product")
    private Set<ProductInOrder> productInOrders = new HashSet<>();

    public Product(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Product() {
    }

    public Set<ProductInOrder> getProductInOrders() {
        return Collections.unmodifiableSet(productInOrders);
    }

    public void addProductInOrders(ProductInOrder productInOrders) {
        productInOrders.setProduct(this);
        this.productInOrders.add(productInOrders);
    }
}
