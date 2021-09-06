package com.cafe.managerassignment.repo;

import com.cafe.managerassignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product save(Product entity);
    public  List<Product> findAll();
    public  Optional<Product> findById(Product entityId);
    public  Product update(Product entity);
    public  Product updateById(Product entity,Long entityId);
    public  void delete(Product entity);
    public  void deleteById(Long entityId);


}
