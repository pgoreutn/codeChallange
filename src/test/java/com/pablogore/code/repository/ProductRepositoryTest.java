package com.pablogore.code.repository;


import com.pablogore.code.entity.ProductEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Container
    private static final MySQLContainer<?> CONTAINER = new MySQLContainer<>("mysql:8.0");

    @Autowired
    private ProductRepository repository;

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }

    @Sql("classpath:/testdata.sql")
    @Test
    public void findAll() throws Exception{
        Pageable paging = PageRequest.of(50, 100, Sort.by("id"));
        Page<ProductEntity> result = repository.findAll(paging);

        Assert.assertTrue(result.getTotalElements()== 284);
        Assert.assertTrue(result.getTotalPages()== 3);

    }
}
