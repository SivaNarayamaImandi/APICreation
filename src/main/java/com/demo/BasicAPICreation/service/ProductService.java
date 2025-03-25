package com.demo.BasicAPICreation.service;

import com.demo.BasicAPICreation.modal.Product;
import com.demo.BasicAPICreation.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Product> getAllProducts() {
        if (productRepository.findAll().isEmpty())
            throw new UsernameNotFoundException("Product Not Found");
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        product.setPassword(encoder.encode(product.getPassword()));
        return productRepository.save(product);
    }

    public boolean updateProduct(int id) {
        for (Product product : productRepository.findAll()) {
            if (product.getId() == id) {
                product.setPrice(product.getPrice() + 1000);
                productRepository.save(product);
                return true;
            }
        }
        throw new UsernameNotFoundException("Product Not Found");
    }

    public boolean deleteProduct(int id) {
        for (Product product : productRepository.findAll()) {
            if (product.getId() == id) {
                productRepository.delete(product);
                return true;
            }
        }
        throw new UsernameNotFoundException("Product Not Found");
    }

    public String verifyProduct(Product product) throws AuthenticationException {
//        System.out.println("Attempting to verify product: " + product.getUsername());
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(product.getUsername(), product.getPassword()));
        if (authentication.isAuthenticated()){
//            System.out.println("Authentication successful for: " + product.getUsername());
            return "Product Is Verified...";
        }
//        System.out.println("Authentication Failed for: " + product.getUsername());
        throw new UsernameNotFoundException("Product Not Found");
    }


}
