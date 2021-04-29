package com.pablogore.code.service;

import com.pablogore.code.dto.ImmutableProductResponse;
import com.pablogore.code.dto.Product;
import com.pablogore.code.dto.ProductResponse;
import com.pablogore.code.entity.ProductEntity;
import com.pablogore.code.mapper.ProductMapper;
import com.pablogore.code.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public ProductResponse create(Product dto){
       ProductEntity entity =  productMapper.toEntity(dto);
       //persist the new product
        entity = productRepository.save(entity);
        ProductResponse r = productMapper.toResponse(entity);
       return productMapper.toResponse(entity);
    }
}
