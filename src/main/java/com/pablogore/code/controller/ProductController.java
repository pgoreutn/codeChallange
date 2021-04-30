package com.pablogore.code.controller;


import com.pablogore.code.dto.Product;
import com.pablogore.code.dto.ProductResponse;
import com.pablogore.code.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for all product functionality i. 
 * This includes searching entities,
 * creating entities, updating entities, etc.
 */

@RestController
@RequestMapping(value = "/product" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )

public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * Create a new product.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ProductResponse createProduct(@RequestBody Product dto) {
        return productService.saveOrUpdate(dto);
    }

    /**
     * Update a product.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ProductResponse updateProduct(@RequestBody Product dto) {
        return productService.saveOrUpdate(dto);
    }


    /**
     * Update a product.
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String name) {
        productService.delete(name);
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> findAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy)
    {

        return new ResponseEntity<>(productService.listOfPaginatedProducts(pageNo, pageSize, sortBy), HttpStatus.OK);
    }
}
