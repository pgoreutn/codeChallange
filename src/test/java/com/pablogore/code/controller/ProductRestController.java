package com.pablogore.code.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablogore.code.dto.ImmutableProduct;
import com.pablogore.code.dto.ImmutableProductResponse;
import com.pablogore.code.dto.Product;
import com.pablogore.code.dto.ProductResponse;
import com.pablogore.code.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    void whenValidInput_save_new_thenReturns200() throws Exception {

        when(productService.saveOrUpdate(any(Product.class))).thenReturn(expectedProductResponse());

        mockMvc.perform(post("/product")
                .content(om.writeValueAsString(product()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", is("Argentina")))
                .andExpect(jsonPath("$.name", is("test 1")));
    }

    @Test
    void whenValidInput_update_new_thenReturns200() throws Exception {

        when(productService.saveOrUpdate(any(Product.class))).thenReturn(expectedProductResponse());

        mockMvc.perform(put("/product")
                .content(om.writeValueAsString(product()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", is("Argentina")))
                .andExpect(jsonPath("$.name", is("test 1")));
    }

    @Test
    void whenValidInput_find_all_paginated_products_thenReturns200() throws Exception {

        //when(productService.saveOrUpdate(any(Product.class))).thenReturn(expectedProductResponse());

        mockMvc.perform(get("/product?pageNo=10&pageSize=20")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    public void delete_product_OK() throws Exception {

        doNothing().when(productService).delete("test");

        mockMvc.perform(delete("/product/test")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/

                .andExpect(status().isOk());

        verify(productService, times(1)).delete("test");
    }


    private ProductResponse expectedProductResponse() {
        return ImmutableProductResponse.builder()
                .id(UUID.randomUUID())
                .country("Argentina")
                .description("test description 1")
                .name("test 1")
                .price(100.1)
                .weight(200.2)
                .build();
    }



    private Product product() {
        return ImmutableProduct.builder()
                .country("Argentina")
                .description("test description 1")
                .name("test 1")
                .price(100.1)
                .weight(200.2)
                .build();
    }
}
