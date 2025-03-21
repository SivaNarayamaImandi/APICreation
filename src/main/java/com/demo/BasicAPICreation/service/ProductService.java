package com.demo.BasicAPICreation.service;

import com.demo.BasicAPICreation.modal.Product;
import com.demo.BasicAPICreation.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean updateProduct(int id) {
        for(Product product : productRepository.findAll()) {
            if(product.getId() == id) {
                product.setPrice(product.getPrice() + 1000);
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        for(Product product : productRepository.findAll()) {
            if(product.getId() == id) {
                productRepository.delete(product);
                return true;
            }
        }
        return false;
    }
}
