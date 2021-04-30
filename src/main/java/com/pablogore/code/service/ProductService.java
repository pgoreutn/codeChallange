package com.pablogore.code.service;

import com.pablogore.code.dto.ImmutableProductResponse;
import com.pablogore.code.dto.Product;
import com.pablogore.code.dto.ProductResponse;
import com.pablogore.code.entity.ProductEntity;
import com.pablogore.code.mapper.ProductMapper;
import com.pablogore.code.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public ProductResponse saveOrUpdate(Product dto){

        //persist the new product
        ProductEntity entity = productRepository.findByName(dto.getName())
                .map(x -> {
                    x.setDescription(dto.getDescription());
                    x.setName(dto.getName());
                    x.setWeight(dto.getWeight());
                    x.setPrice(dto.getPrice());
                    x.setUpdatedAt(new Date());
                    x.setCreatedAt(new Date());
                    return productRepository.save(x);
                })
                .orElseGet(() -> {
                    return productRepository.save(productMapper.toEntity(dto));
                });

        return productMapper.toResponse(entity);
    }

    public void delete(String name){
        productRepository.delete(productRepository.findByName(name).orElseThrow());
    }

    public  Map<String, Object> listOfPaginatedProducts(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<ProductEntity> pagedResult = productRepository.findAll(paging);
        ArrayList<ProductResponse> list = new ArrayList<>();
        if(pagedResult.hasContent()) {
            pagedResult.getContent().forEach(r->{
               list.add(productMapper.toResponse(r));
            });
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", list);
        response.put("currentPage", pagedResult.getNumber());
        response.put("totalItems", pagedResult.getTotalElements());
        response.put("totalPages", pagedResult.getTotalPages());
        return response;
    }
}
