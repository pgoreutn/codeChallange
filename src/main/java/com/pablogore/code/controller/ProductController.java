package com.pablogore.code.controller;


import com.pablogore.code.dto.ImmutableProduct;
import com.pablogore.code.dto.ImmutableProductResponse;
import com.pablogore.code.dto.Product;
import com.pablogore.code.dto.ProductResponse;
import com.pablogore.code.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST Controller for all product functionality i. 
 * This includes searching entities,
 * creating entities, updating entities, etc.
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * Create a product by ID.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ProductResponse createSubmission(@RequestBody Product dto) {
        return productService.create(dto);
    }
}
