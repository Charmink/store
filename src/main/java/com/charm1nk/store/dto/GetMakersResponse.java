package com.charm1nk.store.dto;

import com.charm1nk.store.model.Maker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Форма для получения списка изготовителей")
public class GetMakersResponse {
    @ApiModelProperty("Список изготовителей")
    List<Maker> makers;

    @ApiModelProperty("Колличество изготовителей")
    Integer totalElements;

    public static GetMakersResponse from(List<Maker> makers, Integer totalElements) {
        final var getMakersResponse = new GetMakersResponse();
        getMakersResponse.setMakers(makers);
        getMakersResponse.setTotalElements(totalElements);

        return getMakersResponse;
    }
}
