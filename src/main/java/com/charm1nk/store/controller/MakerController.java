package com.charm1nk.store.controller;

import com.charm1nk.store.dto.GetMakersResponse;
import com.charm1nk.store.service.impl.MakerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MakerController {

    private final MakerServiceImpl makerService;

    @GetMapping("/api/makers")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос на получение полного списка изготовителей")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 400, message = "Бизнес ошибка"),
            @ApiResponse(code = 200, message = "Внутренняя ошибка сервера")
    })
    public GetMakersResponse getMakers() {
        log.info("Get all makers request");
        return makerService.getMakers();
    }
}
