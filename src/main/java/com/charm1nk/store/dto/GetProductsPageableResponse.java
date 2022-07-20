package com.charm1nk.store.dto;

import com.charm1nk.store.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ApiModel(description = "Постраничный список товаров")
public class GetProductsPageableResponse {

    @ApiModelProperty(value = "Общее колличество товаров")
    private Long totalElements;

    @ApiModelProperty(value = "Список товаров")
    private List<GetProductResponse> data;

    public static GetProductsPageableResponse from(List<Product> products, Long totalElements) {
        final var getProductsPageableResponse = new GetProductsPageableResponse();
        getProductsPageableResponse.data = products.stream().map(GetProductResponse::from).collect(Collectors.toList());
        getProductsPageableResponse.totalElements = totalElements;

        return getProductsPageableResponse;
    }
}
