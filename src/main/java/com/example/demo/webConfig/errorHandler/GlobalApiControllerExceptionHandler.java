package com.example.demo.webConfig.errorHandler;


import com.example.demo.webConfig.businessException.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;


/**
 * https://github.com/magiccrafter/spring-boot-exception-handling/blob/master/src/main/java/com/example/rest/error/GlobalControllerExceptionHandler.java
 *
 * https://gist.github.com/matsev/4519323
 *
 * http://www.baeldung.com/global-error-handler-in-a-spring-rest-api
 *
 * Spring Boot REST service exception handling
 *
 *
 *
 *
 */



@RestControllerAdvice("com.example.demo.demo.apiController")
public class GlobalApiControllerExceptionHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    /**
     * HTTP 请求 Method 错误异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) throws IOException {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(400, ex, Util.makeUrl(request));
    }


    /**
     * HTTP 请求 Content Type 错误异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) throws IOException {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(400, ex, Util.makeUrl(request));
    }




    /**
     * 自定义验证异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { BusinessException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError businessException(BusinessException ex, HttpServletRequest request) {

        logger.error("\n ==================== 409 API Error : " + ex.getMessage() + ". Code: " + ex.getCode() + ". Message: " + ex.getErrorMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, Util.makeUrl(request));
    }




    /**
     * POST multipart/form-data 参数数据缺少字段异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { MissingServletRequestPartException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError missingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(400, ex, Util.makeUrl(request));
    }


    /**
     * GET Query 参数数据缺少字段异常 @RequestParam
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError missingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, Util.makeUrl(request));
    }


    /**
     * GET Query 参数数据类型异常 @RequestParam
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, Util.makeUrl(request));
    }


    /**
     * GET Query 参数数据类型异常 @ModelAttribute
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError bindException(BindException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, Util.makeUrl(request));
    }


    /**
     * POST Body 解析JSON 数据类型异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, Util.makeUrl(request));
    }


    /**
     * POST Body 数据验证异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {
        MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, Util.makeUrl(request));
    }







    /**
     * 数据验证异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {
        ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 API Error : " + ex.getMessage(), ex);
        return new ApiError(1001, ex.getMessage());
    }



    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError noHandlerFoundException(Exception ex, HttpServletRequest request) throws IOException {

        logger.error(" \n ==================== 404 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 5, ""));
        return new ApiError(404, ex, Util.makeUrl(request));
    }





    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError unknownException(Exception ex, HttpServletRequest request) {

        logger.error(" \n ==================== 500 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 5, ""));
        return new ApiError(500, ex, Util.makeUrl(request));
        
    }






}

