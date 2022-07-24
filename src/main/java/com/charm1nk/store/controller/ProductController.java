package com.charm1nk.store.controller;

import com.charm1nk.store.dto.CreateProductRequest;
import com.charm1nk.store.dto.CreateProductResponse;
import com.charm1nk.store.dto.GetProductResponse;
import com.charm1nk.store.dto.GetProductsResponse;
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

    @PostMapping("/api/products")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос на создание продукта")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 400, message = "Бизнес ошибка"),
            @ApiResponse(code = 200, message = "Внутренняя ошибка сервера")
    })
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        log.info("Create product request {}", createProductRequest);
        return productService.createProduct(createProductRequest);
    }

    @GetMapping("/api/products/{productId}")
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

    @GetMapping("/api/products")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос на постраничное получение товаров")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 400, message = "Бизнес ошибка"),
            @ApiResponse(code = 200, message = "Внутренняя ошибка сервера")
    })
    public GetProductsResponse getProducts(@RequestParam Integer page, @RequestParam Integer size) {
        log.info("Get pageable product list request, page: {}, size: {}", page, size);
        return productService.getProducts(page, size);
    }

    @GetMapping("/api/products/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос на полнотекстовый поиск товаров")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 400, message = "Бизнес ошибка"),
            @ApiResponse(code = 200, message = "Внутренняя ошибка сервера")
    })
    public GetProductsResponse getProductsFullSearch(@RequestParam String text) {
        log.info("Get product list for full search text: {} request", text);
        return productService.getProductsSearch(text);
    }
}
