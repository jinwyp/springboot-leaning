package com.example.demo.webConfig.errorHandler;


import com.example.demo.webConfig.businessException.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


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
    public ApiError noHandlerFoundException(Exception ex, HttpServletRequest request) throws IOException {

        logger.error(" \n ==================== 404 Error : " +  ex.getMessage() + "\n ===== Request Url: " + makeUrl(request) + "\n" + stackTraceToString(ex, 5, ""));
        return new ApiError(404, ex, makeUrl(request));
    }


    @ExceptionHandler(value = { BusinessException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError businessException(BusinessException ex, HttpServletRequest request) {

        logger.error("\n ==================== 409 Error : " +  ex.getMessage()  + ". Code: " + ex.getCode() + ". Message: "+ ex.getErrorMessage() + "\n ===== Request Url: " + makeUrl(request) + "\n" + stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, makeUrl(request));
    }




    /**
     * GET Query 参数数据验证异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {
        MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError missingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {

        logger.error(" \n ==================== 400 Error : " +  ex.getMessage() + "\n ===== Request Url: " + makeUrl(request)  + "\n" + stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, makeUrl(request));
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

        logger.error(" \n ==================== 400 Error : " +  ex.getMessage() + "\n ===== Request Url: " + makeUrl(request)  + "\n" + stackTraceToString(ex, 0, "com.example.demo"));
        return new ApiError(ex, makeUrl(request));
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

        logger.error(" \n ==================== 400 Error : ", ex.getMessage(), ex);
        return new ApiError(1001, ex.getMessage());
    }




    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError unknownException(Exception ex, HttpServletRequest request) {

        logger.error(" \n ==================== 500 Error : ", ex.getMessage(), ex);
        return new ApiError(500, ex.getMessage());
    }






    /**
     * 只返回指定包中的异常堆栈信息
     * https://github.com/0opslab/utils/blob/master/src/main/java/com/opslab/util/ExceptionUtil.java
     * 可以通过使用我开源的工具包获取
     *
     * @param exceptionOriginal 异常信息
     * @param packageName 只转换某个包下的信息
     * @param showLines 只显示几行
     * @return string
     */

    public static String stackTraceToString(Throwable exceptionOriginal, int showLines, String packageName) {
        StringWriter sw = new StringWriter();
        exceptionOriginal.printStackTrace(new PrintWriter(sw, true));

        String tempString = sw.toString();

        if (packageName == null) {
            return tempString;
        }

        String[] arrs = tempString.split("\n");
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(arrs[0] + "\n");

        if (showLines > 0 ) {

            if (showLines > arrs.length) {
                showLines = arrs.length;
            }

            for (int i = 1; i < showLines; i++) {
                String temp = arrs[i];
                sbuf.append(temp + "\n");
            }
        } else {

            if (packageName.isEmpty()) {
                return tempString;
            }

            for (int i = 1; i < arrs.length; i++) {
                String temp = arrs[i];
                if (temp != null && temp.indexOf(packageName) > 0) {
                    sbuf.append(temp + "\n");
                }
            }
        }

        return sbuf.toString();
    }




    /**
     * https://stackoverflow.com/questions/1490821/whats-the-best-way-to-get-the-current-url-in-spring-mvc
     *
     * @param request
     * @return
     */
    public static String makeUrl(HttpServletRequest request) {

        if (request.getQueryString() == null) {
            return request.getRequestURL().toString();
        }

        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }


}

