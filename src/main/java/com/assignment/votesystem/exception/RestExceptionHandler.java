package com.assignment.votesystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(GenericException genericException){
        ErrorMessage errorMessage= ErrorMessage.builder()
                .errorMessage(genericException.getErrorMessage())
                .httpStatus(genericException.getHttpStatus())
                .build();
        return new ResponseEntity<>(errorMessage,genericException.getHttpStatus());
    }
}
