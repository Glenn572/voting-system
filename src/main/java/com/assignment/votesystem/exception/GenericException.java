package com.assignment.votesystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
public class GenericException extends RuntimeException{

    private String errorMessage;
    private HttpStatus httpStatus;

    public GenericException(String errorMessage,HttpStatus httpStatus){
        super(errorMessage);
        this.errorMessage=errorMessage;
        this.httpStatus=httpStatus;

    }
}
