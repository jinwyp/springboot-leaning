package com.example.demo.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;


/**
 * https://github.com/magiccrafter/spring-boot-exception-handling/blob/master/src/main/java/com/example/rest/error/GlobalControllerExceptionHandler.java
 *
 *
 * Spring Boot REST service exception handling
 *
 *
 *
 *
 */



@RestControllerAdvice
public class GlobalControllerExceptionHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {

        logger.error(" \n ==================== 404 Error : ", ex.getMessage(), ex);
        return new ApiErrorResponse(404, ex.getMessage());
    }



    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {

        logger.error(" \n ==================== 400 Error : ", ex.getMessage(), ex);
        return new ApiErrorResponse(1001, ex.getMessage());
    }


    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        logger.error(" \n ==================== 400 Error : ", ex.getMessage(), ex);
        return new ApiErrorResponse(1002, ex.getMessage());
    }



    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception ex) {

        logger.error(" \n ==================== 500 Error : ", ex.getMessage(), ex);
        return new ApiErrorResponse(500, ex.getMessage());
    }

}

