package com.charm1nk.store.service;

import com.charm1nk.store.dto.CreateProductRequest;
import com.charm1nk.store.dto.GetProductResponse;

public interface ProductService {
    void createProduct(CreateProductRequest createProductRequest);
    GetProductResponse getProduct(Long productId);

}
