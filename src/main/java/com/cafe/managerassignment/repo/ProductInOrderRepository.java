package com.cafe.managerassignment.repo;

import com.cafe.managerassignment.model.Product;
import com.cafe.managerassignment.model.ProductInOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductInOrderRepository extends CrudRepository<ProductInOrder, Long> {
    List<ProductInOrder> findByProduct_Id(Long product_id);

    List<ProductInOrder> findByOrder_id(Long order_id);
}
