package com.cafe.managerassignment.service;

import com.cafe.managerassignment.exceptions.DataNotFoundException;
import com.cafe.managerassignment.model.Product;
import com.cafe.managerassignment.model.restmodel.ProductRequestModel;
import com.cafe.managerassignment.model.restmodel.ProductResponseModel;
import com.cafe.managerassignment.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product requestToProduct(ProductRequestModel producerRequestModel) {
        Product producer = new Product();
        producer.setTitle(producerRequestModel.getTitle());
        producer.setDescription(producerRequestModel.getDescription());
        return producer;
    }

    private ProductResponseModel productToResponse(Product product) {
        ProductResponseModel productResponseModel = new ProductResponseModel();
        productResponseModel.setTitle(product.getTitle());
        productResponseModel.setDescription(product.getDescription());
        productResponseModel.setPrice(product.getPrice());
        return productResponseModel;
    }

    public ProductResponseModel createProduct(ProductRequestModel productRequestModel) {
        Product product = requestToProduct(productRequestModel);
        productRepository.save(product);
        return productToResponse(product);
    }

    public List<ProductResponseModel> findAll() {
        List<Product> all = productRepository.findAll();
        return all.stream()
                .map(this::productToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseModel findById(Long id) {
        Product product;
        if (productRepository.findById(id).isPresent()) {
            product = productRepository.findById(id).get();
        } else throw new DataNotFoundException("The Product not found");
        return productToResponse(product);
    }

    public ProductResponseModel updateProduct(Long id, ProductRequestModel productRequestModel) {
        Product product;
        if (productRepository.findById(id).isPresent()) {
            product = productRepository.findById(id).get();
            product.setDescription(product.getDescription());
            product.setTitle(productRequestModel.getTitle());
            productRepository.save(product);
        } else throw new DataNotFoundException("The Product not found");
        return productToResponse(product);
    }
/*
    public Set<ProductResponseModel> findByUser(Long id) {
        Set<Product> all = productRepository.findAll(id);
        return all.stream()
                .map(this::productToResponse)
                .collect(Collectors.toSet());
    } */

    public ProductResponseModel deleteProduct(Long id) {
        productRepository.deleteById(id);
        return null;
    }
}
