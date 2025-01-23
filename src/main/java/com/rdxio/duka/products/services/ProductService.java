/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.rdxio.duka.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.rdxio.duka.products.dtos.ProductDto;
import com.rdxio.duka.products.entities.Product;
import com.rdxio.duka.products.repositories.ProductRepository;

/**
 *
 * @author 7X
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto) {
        Product product = new Product(
            productDto.name(),
            productDto.description(),
            productDto.price(),
            productDto.imageUrl(),
            productDto.category(),
            productDto.vatCategory()
        );
        productRepository.save(product);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }
}
