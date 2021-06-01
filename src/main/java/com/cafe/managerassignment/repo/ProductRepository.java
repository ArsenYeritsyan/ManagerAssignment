package com.cafe.managerassignment.repo;

import com.cafe.managerassignment.model.Order;
import com.cafe.managerassignment.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

}
