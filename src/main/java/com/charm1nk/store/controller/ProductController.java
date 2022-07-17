package com.charm1nk.store.controller;

import com.charm1nk.store.dto.CreateProductRequest;
import com.charm1nk.store.dto.GetProductResponse;
import com.charm1nk.store.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/product")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос на создание продукта")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 400, message = "Бизнес ошибка"),
            @ApiResponse(code = 200, message = "Внутренняя ошибка сервера")
    })
    public void createProduct(@RequestBody CreateProductRequest createProductRequest) {
        log.info("Create product request {}", createProductRequest);
        productService.createProduct(createProductRequest);
    }

    @GetMapping("/api/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос на получение детализированной информации продукта")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 400, message = "Бизнес ошибка"),
            @ApiResponse(code = 200, message = "Внутренняя ошибка сервера")
    })
    public GetProductResponse getProduct(@PathVariable Long productId) {
        log.info("Get product request {}", productId);
        return productService.getProduct(productId);
    }
}
