package com.demo.BasicAPICreation.service;

import com.demo.BasicAPICreation.modal.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>(
            List.of(
                    new Product(1, "Iphone", 54000, 32, "It's a high demand phone now"),
                    new Product(2, "Samsung", 45000, 36, "It's a medium demand phone now")
            )
    );

    public List<Product> getAllProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        products.add(product);
        return true;
    }
}
