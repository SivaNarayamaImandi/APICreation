package com.demo.BasicAPICreation.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Product {
    @Id
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_price")
    @Min(value = 0, message = "Price must be at least 0")
    private double price;
    @Column(name = "product_quantity")
    @Min(value = 0, message = "Quantity must be at least 0")
    private int quantity;
    @Column(name = "product_description")
    private String description;
}
