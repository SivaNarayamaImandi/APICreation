package com.demo.BasicAPICreation.repo;

import com.demo.BasicAPICreation.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
