package com.charm1nk.store.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ApiModel(description = "Данная структура возвращается при успешном создании товара")
public class CreateProductResponse {

    @ApiModelProperty("Идентификатор товара")
    private final Long productId;
}
