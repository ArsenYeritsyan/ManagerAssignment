package com.cafe.managerassignment.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

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
}
