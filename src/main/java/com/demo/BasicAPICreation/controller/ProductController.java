package com.demo.BasicAPICreation.controller;

import com.demo.BasicAPICreation.modal.Product;
import com.demo.BasicAPICreation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/greet")
    public String greet() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        if (productService.getAllProducts() == null || productService.getAllProducts().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        System.out.println(product);
        if(productService.addProduct(product))
            return ResponseEntity.ok("Product added successfully");
        return ResponseEntity.badRequest().body("Product not added");
    }
}
