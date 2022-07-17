package com.charm1nk.store.dto;

import com.charm1nk.store.model.Currency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "Параметры для создания товара")
public class CreateProductRequest {

    @ApiModelProperty(value = "Наименование")
    @NotBlank(message = "name can not be blank")
    private String name;

    @ApiModelProperty(value = "Описание")
    @NotBlank(message = "description can not be blank")
    private String description;

    @ApiModelProperty(value = "Цена")
    @NotBlank(message = "price can not be blank")
    private Double price;

    @ApiModelProperty(value = "Валюта")
    @NotBlank(message = "currency can not be blank")
    private Currency currency;

    @ApiModelProperty(value = "Наличие")
    @NotBlank(message = "available can not be blank")
    private Boolean available;

    @ApiModelProperty(value = "Гарантия")
    @NotBlank(message = "price can not be blank")
    private Long guarantee;

    @ApiModelProperty(value = "Наименование компании производителя")
    @NotBlank(message = "makerName can not be blank")
    private String makerName;

    @ApiModelProperty(value = "Идентификатор раздела")
    @NotBlank(message = "partitionId can not be blank")
    private Long partitionId;
}
