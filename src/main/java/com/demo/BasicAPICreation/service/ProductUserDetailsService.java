package com.demo.BasicAPICreation.service;

import com.demo.BasicAPICreation.modal.Product;
import com.demo.BasicAPICreation.modal.ProductPrincipal;
import com.demo.BasicAPICreation.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductUserDetailsService implements UserDetailsService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Attempting to load user: " + username);
        Product product = productRepository.findByUsername(username);
        if (product == null) {
//            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("User Not Found");
        }
//        System.out.println("User found: " + product.getUsername());
        return new ProductPrincipal(product);
    }
}
