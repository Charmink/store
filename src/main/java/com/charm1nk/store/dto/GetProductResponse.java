package com.charm1nk.store.dto;

import com.charm1nk.store.model.Currency;
import com.charm1nk.store.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Детализированная информация по товару")
public class GetProductResponse {

    @ApiModelProperty(value = "Идентификатор")
    private Long id;

    @ApiModelProperty(value = "Наименование")
    private String name;

    @ApiModelProperty(value = "Описание")
    private String description;

    @ApiModelProperty(value = "Цена")
    private Double price;

    @ApiModelProperty(value = "Валюта")
    private Currency currency;

    @ApiModelProperty(value = "Наличие")
    private Boolean available;

    @ApiModelProperty(value = "Гарантия")
    private Long guarantee;

    @ApiModelProperty(value = "Наименование компании производителя")
    private String makerName;

    @ApiModelProperty(value = "Наименование раздела товара")
    private String partitionName;

    public static GetProductResponse from(Product product) {
        final var getProductResponse = new GetProductResponse();

        getProductResponse.setId(product.getId());
        getProductResponse.setAvailable(product.getAvailable());
        getProductResponse.setCurrency(product.getCurrency());
        getProductResponse.setDescription(product.getDescription());
        getProductResponse.setGuarantee(product.getGuarantee());
        getProductResponse.setName(product.getName());
        getProductResponse.setPrice(product.getPrice());
        getProductResponse.setPartitionName(product.getPartition().getName());
        getProductResponse.setMakerName(product.getMaker().getName());

        return getProductResponse;
    }
}
