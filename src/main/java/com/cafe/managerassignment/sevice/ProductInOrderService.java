package com.cafe.managerassignment.sevice;

import com.cafe.managerassignment.model.Product;
import com.cafe.managerassignment.model.ProductInOrder;
import com.cafe.managerassignment.repo.ProductInOrderRepository;

import java.util.List;

public class ProductInOrderService {
    private ProductInOrderRepository productInOrderRepository;

    public ProductInOrderService(ProductInOrderRepository productInOrderRepository) {
        this.productInOrderRepository = productInOrderRepository;
    }

    public List<ProductInOrder> findAllByProduct(Product product) {
        List<ProductInOrder> products = productInOrderRepository.findByProduct_Id(product.getId());
        return products;
    }

    public ProductInOrder findById(String id) {
        Long productId = Long.parseLong(id);
        return productInOrderRepository.findById(productId)
                .orElseThrow(IllegalStateException::new);
    }

    public void save(ProductInOrder productInOrder) {
        productInOrderRepository.save(productInOrder);
    }
}
