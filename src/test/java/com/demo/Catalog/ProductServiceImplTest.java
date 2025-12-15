package com.demo.Catalog;



import com.demo.Catalog.dto.request.CreateProductRequest;
import com.demo.Catalog.dto.request.UpdateProductRequest;
import com.demo.Catalog.dto.response.ProductResponse;
import com.demo.Catalog.entity.Product;
import com.demo.Catalog.exception.ProductNotFoundException;
import com.demo.Catalog.respository.ProductRepository;
import com.demo.Catalog.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldCreateProductSuccessfully() {

        CreateProductRequest request = new CreateProductRequest();
        request.setName("Macbook Pro");
        request.setDescription("M3");
        request.setPrice(BigDecimal.valueOf(95000));

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Macbook Pro");
        savedProduct.setPrice(BigDecimal.valueOf(95000));
        savedProduct.setActive(true);

        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        ProductResponse response = productService.create(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Macbook Pro", response.getName());
        assertTrue(response.getActive());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldGetProductById() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Macbook");

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        ProductResponse response = productService.getById(1L);

        assertEquals(1L, response.getId());
        assertEquals("Macbook", response.getName());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        when(productRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.getById(99L)
        );
    }

    @Test
    void shouldDeactivateProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setActive(true);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        productService.deactivate(1L);

        assertFalse(product.getActive());
    }

    @Test
    void shouldUpdateProduct() {

        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        UpdateProductRequest request = new UpdateProductRequest();
        request.setName("Updated");
        request.setDescription("Updated desc");
        request.setPrice(BigDecimal.valueOf(1000));

        ProductResponse response = productService.update(1L, request);

        assertEquals("Updated", response.getName());
        assertEquals(BigDecimal.valueOf(1000), response.getPrice());
    }
}
