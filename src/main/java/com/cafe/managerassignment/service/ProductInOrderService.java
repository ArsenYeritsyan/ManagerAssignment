package com.cafe.managerassignment.service;

import com.cafe.managerassignment.model.Product;
import com.cafe.managerassignment.model.ProductInOrder;
import com.cafe.managerassignment.repo.ProductInOrderRepository;

import java.time.LocalDateTime;
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

    public ProductInOrder findById(Long id) {
        return productInOrderRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    public void save(ProductInOrder productInOrder) {
        productInOrderRepository.save(productInOrder);
    }

    public void update(ProductInOrder productInOrder) {
        ProductInOrder entity = this.findById(productInOrder.getId());

        if(productInOrder.getProduct() == null)
            productInOrder.setProduct(entity.getProduct());
        if(productInOrder.getAmount() == null)
            productInOrder.setAmount(entity.getAmount());
        if(productInOrder.getCreator() == null)
            productInOrder.setCreator(entity.getCreator());
        if(productInOrder.getQuantity() == null)
            productInOrder.setQuantity(entity.getQuantity());
        if(productInOrder.getStatus() == null)
            productInOrder.setStatus(entity.getStatus());
        productInOrder.setModified(LocalDateTime.now());
        this.save(productInOrder);
    }

    public void delete(Long uuid) {
        ProductInOrder productInOrder = this.findById(uuid);
        this.productInOrderRepository.delete(productInOrder);
    }
}
