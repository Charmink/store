package com.charm1nk.store.exception;

import com.charm1nk.store.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class StoreExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> onMakerNotExistException(MakerNotExistException e) {
        log.error("MakerNotExistException", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .code("ERROR.MAKER_NOT_EXIST")
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> onPartitionNotExistException(PartitionNotExistException e) {
        log.error("PartitionNotExistException", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .code("ERROR.PARTITION_NOT_EXIST")
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> onProductNotExistException(ProductNotExistException e) {
        log.error("ProductNotExistException", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .code("ERROR.PRODUCT_NOT_EXIST")
                                .message(e.getMessage())
                                .build()
                );
    }
}
