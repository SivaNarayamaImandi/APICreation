package com.demo.BasicAPICreation.service;

import com.demo.BasicAPICreation.modal.Product;
import com.demo.BasicAPICreation.modal.ProductPrincipal;
import com.demo.BasicAPICreation.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements UserDetailsService {

    @Autowired
    ProductRepository productRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Product product = productRepository.findByUsername(username);
        if (product == null)
            throw new UsernameNotFoundException("User Not Found");
        return new ProductPrincipal(product);
    }

    public List<Product> getAllProducts() {
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
        return false;
    }

    public boolean deleteProduct(int id) {
        for (Product product : productRepository.findAll()) {
            if (product.getId() == id) {
                productRepository.delete(product);
                return true;
            }
        }
        return false;
    }
}
