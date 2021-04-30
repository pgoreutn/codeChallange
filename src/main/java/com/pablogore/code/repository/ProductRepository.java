package com.pablogore.code.repository;

import com.pablogore.code.entity.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity,String> {
    Optional<ProductEntity> findByName(String name);
}
