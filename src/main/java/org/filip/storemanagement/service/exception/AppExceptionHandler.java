package org.filip.storemanagement.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<AppError> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(ex.getHttpErrorCode())
                .body(new AppError(ex.getHttpErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<AppError> handleInvalidInputException(InvalidInputException ex) {
        return ResponseEntity.status(ex.getHttpErrorCode())
                .body(new AppError(ex.getHttpErrorCode(), ex.getErrorMessage()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<AppError> handleAnyOtherException(RuntimeException ex) {
        return ResponseEntity.status(500)
                .body(new AppError(500, "We've encountered an unexpected issue. We'll try to work on it as soon as possible"));
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.status(400)
                .body(new AppError(400, ex.getMessage()));
    }
}