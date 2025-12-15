package com.demo.Catalog.mapper;

import com.demo.Catalog.dto.request.CreateProductRequest;
import com.demo.Catalog.dto.response.ProductResponse;
import com.demo.Catalog.entity.Product;

public class ProductMapper {

    private ProductMapper() {}

    public static Product toEntity(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        return product;
    }

    public static ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setActive(product.getActive());
        return response;
    }
}
