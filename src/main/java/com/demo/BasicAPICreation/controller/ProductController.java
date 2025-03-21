package com.demo.BasicAPICreation.controller;

import com.demo.BasicAPICreation.modal.Product;
import com.demo.BasicAPICreation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        if(productService.addProduct(product)!=null)
            return new ResponseEntity<String>("Product added successfully",HttpStatus.CREATED);
        return new ResponseEntity<String>("Url is not valid...",HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id){
        if (productService.updateProduct(id))
            return new ResponseEntity<String>("Product updated successful...", HttpStatus.CREATED);
        return new ResponseEntity<String>("Product not found successful...", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        if(productService.deleteProduct(id))
            return new ResponseEntity<String>("Product deleted successfully...",HttpStatus.OK);
        return new ResponseEntity<String>("Product not found successfully...",HttpStatus.NOT_FOUND);
    }
}
