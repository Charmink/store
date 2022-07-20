package com.charm1nk.store.service;

import com.charm1nk.store.dto.CreateProductRequest;
import com.charm1nk.store.dto.CreateProductResponse;
import com.charm1nk.store.dto.GetProductResponse;
import com.charm1nk.store.dto.GetProductsPageableResponse;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
    GetProductResponse getProduct(Long productId);

    GetProductsPageableResponse getProducts(Integer page, Integer size);

}
