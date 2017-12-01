package com.example.demo.webConfig.errorHandler;

import com.example.demo.webConfig.businessException.BusinessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.Field;
import java.util.List;

public class ApiError {

    private int code;
    private String message;
    private String field;
    private String exceptionName;
    private String url;


    public ApiError(int code) {
        this.code = code;
        this.message = "";
    }


    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiError(int code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public ApiError(int code, String message, String url, String exceptionName) {
        this.code = code;
        this.message = message;
        this.exceptionName = exceptionName;
        this.url = url;
    }

    public ApiError(int code, String message, String url, String exceptionName, String field) {
        this.code = code;
        this.message = message;
        this.field = field;
        this.url = url;
        this.exceptionName = exceptionName;
    }



    public ApiError(BusinessException ex, String url) {
        this.code = ex.getCode();
        this.message = ex.getErrorMessage();
        this.field = ex.getField();
        this.url = url;
        this.exceptionName = ex.getClass().getName();
    }

    /**
     * GET Query 参数数据缺少字段异常 @RequestParam
     * @param ex
     * @param url
     */
    public ApiError(MissingServletRequestParameterException ex, String url) {
        this.code = 4000;
        this.message = "Field validation error. " + ex.getMessage();
        this.field = ex.getParameterName();
        this.url = url;
        this.exceptionName = ex.getClass().getName();
    }


    /**
     * GET Query 参数数据类型异常 @RequestParam
     * @param ex
     * @param url
     */
    public ApiError(MethodArgumentTypeMismatchException ex, String url) {
        this.code = 4000;
        this.message = "Field validation error, " + ex.getName() + " field " + ex.getErrorCode() + ". " + ex.getMessage();
        this.field = ex.getName();
        this.url = url;
        this.exceptionName = ex.getClass().getName();
    }

    /**
     * GET Query 参数数据类型异常 @ModelAttribute
     * @param ex
     * @param url
     */
    public ApiError(BindException ex, String url) {
        BindingResult result = ex.getBindingResult();

        for (FieldError singleField : result.getFieldErrors()){
            this.field = singleField.getObjectName() + "." + singleField.getField();
            this.message = "Field validation error, " + singleField.getField() + " field " + singleField.getDefaultMessage();
        }

        this.code = 4000;
        this.url = url;
        this.exceptionName = ex.getClass().getName();
    }

    /**
     * POST Body 解析JSON 数据类型异常
     * @param ex
     * @param url
     */
    public ApiError(HttpMessageNotReadableException ex, String url) {

        Throwable mostSpecificCause = ex.getMostSpecificCause();

        this.code = 4000;
        this.field = "";
        this.message = "Field validation error. ";
        this.url = url;
        this.exceptionName = ex.getClass().getName();

        if (mostSpecificCause != null) {
            this.exceptionName = this.exceptionName + " | " + mostSpecificCause.getClass().getName();
            this.message = this.message + mostSpecificCause.getMessage();
        }
    }


    /**
     * POST Body 数据验证异常
     * @param ex
     * @param url
     */
    public ApiError(MethodArgumentNotValidException ex, String url) {

        BindingResult result = ex.getBindingResult();

        for (FieldError singleField : result.getFieldErrors()){
            this.field = singleField.getObjectName() + "." + singleField.getField();
            this.message = "Field validation error, " + singleField.getField() + " field " + singleField.getDefaultMessage();
        }

        this.code = 4000;
        this.url = url;
        this.exceptionName = ex.getClass().getName();
    }




    public ApiError(int code, Exception ex, String url) {
        this.code = code;
        this.message = ex.getMessage();
        this.url = url;
        this.exceptionName = ex.getClass().getName();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}
