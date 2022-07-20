package com.charm1nk.store.unit.controller;


import com.charm1nk.store.common.JsonConverter;
import com.charm1nk.store.controller.ProductController;
import com.charm1nk.store.dto.CreateProductRequest;
import com.charm1nk.store.dto.CreateProductResponse;
import com.charm1nk.store.exception.StoreExceptionHandler;
import com.charm1nk.store.model.Currency;
import com.charm1nk.store.service.ProductService;
import com.charm1nk.store.service.impl.ProductServiceImpl;
import com.charm1nk.store.unit.UnitTests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.charm1nk.store.common.TestUtils.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(PER_CLASS)
public class ProductControllerTest extends UnitTests {

    private static final String PRODUCT_API = "/api/products";
    private final ProductService productService = Mockito.mock(ProductServiceImpl.class);
    private MockMvc mockMvc;

    @BeforeAll
    public void beforeAll() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductController(productService))
                .setControllerAdvice(StoreExceptionHandler.class)
                .setMessageConverters(JsonConverter.getInstance())
                .build();
    }

    @Test
    void shouldSuccessfullyCreateProduct() throws Exception {
        final var createProductRequest = generateCreateProductRequest();
        final var createProductResponse = new CreateProductResponse(randomPositiveNumber().longValue());

        when(productService.createProduct(createProductRequest)).thenReturn(createProductResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT_API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createProductRequest))

        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.productId").value(createProductResponse.getProductId()));

    }

    private CreateProductRequest generateCreateProductRequest() {
        final var createProductRequest = new CreateProductRequest();

        createProductRequest.setAvailable(true);
        createProductRequest.setCurrency(Currency.RUR);
        createProductRequest.setDescription(randomString());
        createProductRequest.setGuarantee(randomNumber().longValue());
        createProductRequest.setName(randomString());
        createProductRequest.setPrice(randomNumber().doubleValue());
        createProductRequest.setMakerName(randomString());
        createProductRequest.setPartitionId(randomPositiveNumber().longValue());

        return createProductRequest;
    }
}
