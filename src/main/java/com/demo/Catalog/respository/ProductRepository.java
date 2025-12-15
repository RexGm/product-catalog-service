package com.demo.Catalog.respository;

import com.demo.Catalog.dto.request.UpdateProductRequest;
import com.demo.Catalog.dto.response.ProductResponse;
import com.demo.Catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
    Page<Product> findByActiveTrue(Pageable pageable);


}
