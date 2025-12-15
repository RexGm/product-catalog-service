package com.demo.Catalog.service;

import com.demo.Catalog.dto.request.CreateProductRequest;
import com.demo.Catalog.dto.request.UpdateProductRequest;
import com.demo.Catalog.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    Page<ProductResponse> getAll(int page, int size, String sortBy);

    ProductResponse getById(Long id);

    void deactivate(Long id);

    void activate(Long id);

    ProductResponse update(Long id, UpdateProductRequest request);

}
