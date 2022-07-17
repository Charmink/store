package com.charm1nk.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ApiModel(description = "При ошибках возвращается данная структура")
public class ErrorResponse {

    @ApiModelProperty(value = "Код ошибки")
    @JsonProperty("code")
    private String code;

    @ApiModelProperty(value = "Описание ошибки")
    @JsonProperty("message")
    private String message;
}
