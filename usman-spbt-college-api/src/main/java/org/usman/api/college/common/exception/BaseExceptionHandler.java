package org.usman.api.college.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.usman.api.college.common.response.ErrorResponse;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<?> internalServerError(HttpServerErrorException.InternalServerError ex) {
        log.error("InternalServerError occurred : {}", ex.getMessage());
        return ResponseEntity.of(Optional.of(new ErrorResponse(601, "INT_ERR",  ex.getMessage())));
    }

    @ResponseStatus(value = BAD_GATEWAY)
    @ExceptionHandler(value = {HttpServerErrorException.BadGateway.class})
    public ResponseEntity<?> badGatewayError(HttpServerErrorException.BadGateway ex) {
        log.error("BadGateway Error occurred : {}", ex.getMessage());
        return ResponseEntity.of(Optional.of(new ErrorResponse(602, "BAD_GT",  ex.getMessage())));
    }

    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<?> businessException(BusinessException ex) {
        log.error("BusinessException Error occurred : {}", ex.getMessage());
        return ResponseEntity.of(Optional.of(new ErrorResponse(603, "BUS_ERR",  ex.getErrorMessage())));
    }

    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> runTimeException(RuntimeException ex) {
        log.error("RuntimeException Error occurred : {}", ex.getMessage());
        return ResponseEntity.of(Optional.of(new ErrorResponse(699, "GEN_ERR",  ex.getMessage())));
    }

}
