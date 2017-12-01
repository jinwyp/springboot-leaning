package com.example.demo.webConfig.errorHandler;

import com.example.demo.webConfig.businessException.BusinessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

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

    public ApiError(MissingServletRequestParameterException ex, String url) {
        this.code = 4000;
        this.message = "Field validation error. " + ex.getMessage();
        this.field = ex.getParameterName();
        this.url = url;
        this.exceptionName = ex.getClass().getName();
    }


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
