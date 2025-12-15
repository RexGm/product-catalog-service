package com.demo.Catalog.service.impl;

import com.demo.Catalog.dto.request.CreateProductRequest;
import com.demo.Catalog.dto.request.UpdateProductRequest;
import com.demo.Catalog.dto.response.ProductResponse;
import com.demo.Catalog.entity.Product;
import com.demo.Catalog.exception.ProductNotFoundException;
import com.demo.Catalog.mapper.ProductMapper;
import com.demo.Catalog.respository.ProductRepository;
import com.demo.Catalog.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {

        Product product = ProductMapper.toEntity(request);

        product.setActive(true);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedBy("SYSTEM");

        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public Page<ProductResponse> getAll(int page, int size, String sortBy) {

        PageRequest pageRequest =
                PageRequest.of(page, size, Sort.by(sortBy).descending());

        return productRepository
                .findByActiveTrue(pageRequest)
                .map(ProductMapper::toResponse);
    }

    @Override
    public ProductResponse getById(Long id) {
        return ProductMapper.toResponse(getProductOrThrow(id));
    }

    @Override
    public void deactivate(Long id) {
        Product product = getProductOrThrow(id);
        product.setActive(false);
        product.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void activate(Long id) {
        Product product = getProductOrThrow(id);
        product.setActive(true);
        product.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public ProductResponse update(Long id, UpdateProductRequest request) {

        Product product = getProductOrThrow(id);

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setUpdatedAt(LocalDateTime.now());

        return ProductMapper.toResponse(product);
    }

    private Product getProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
