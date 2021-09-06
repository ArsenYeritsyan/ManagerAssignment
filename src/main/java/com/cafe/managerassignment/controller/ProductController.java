package com.cafe.managerassignment.controller;

import com.cafe.managerassignment.model.restmodel.ProductRequestModel;
import com.cafe.managerassignment.model.restmodel.ProductResponseModel;
import com.cafe.managerassignment.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductResponseModel> createProduct(@RequestBody ProductRequestModel productRequestModel) {
        ProductResponseModel product = productService.createProduct(productRequestModel);
        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> updateProduct(@PathVariable Long id, @RequestBody ProductRequestModel productRequestModel) {
        ProductResponseModel productResponseModel = productService.updateProduct(id, productRequestModel);
        return ResponseEntity.ok(productResponseModel);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductResponseModel>> findAll() {
        List<ProductResponseModel> all = productService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> findById(@PathVariable Long id) {
        ProductResponseModel productResponseModel = productService.findById(id);
        return ResponseEntity.ok(productResponseModel);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductResponseModel> deleteProduct(@PathVariable Long id) {
        ProductResponseModel delete = productService.deleteProduct(id);
        return ResponseEntity.ok(delete);
    }
}
