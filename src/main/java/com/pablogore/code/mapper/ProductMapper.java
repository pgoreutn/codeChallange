package com.pablogore.code.mapper;

import com.pablogore.code.dto.Product;
import com.pablogore.code.dto.ProductResponse;
import com.pablogore.code.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductEntity toEntity(Product dto);
    ProductResponse toResponse(ProductEntity entity);
}
