package com.example.demo.webConfig.errorHandler;


import com.example.demo.webConfig.businessException.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
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
    public ApiErrorResponse noHandlerFoundException(Exception ex) {

        logger.error(" \n ==================== 404 Error : " +  ex.getMessage(), ex);
        return new ApiErrorResponse(404, ex.getMessage());
    }


    @ExceptionHandler(value = {
            BusinessException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse businessException(BusinessException ex) {

        logger.error("\n ==================== 400 Error : " +  ex.getMessage()  + ". Code: " + ex.getCode() + ". Message: "+ ex.getErrorMessage() + "\n" + stackTraceToString(ex, "com.example.demo", 0));
        return new ApiErrorResponse(ex.getCode(), ex.getErrorMessage());
    }


    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {

        logger.error(" \n ==================== 400 Error : ", ex.getMessage(), ex);
        return new ApiErrorResponse(1001, ex.getMessage());
    }




    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception ex) {

        logger.error(" \n ==================== 500 Error : ", ex.getMessage(), ex);
        return new ApiErrorResponse(500, ex.getMessage());
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

    public static String stackTraceToString(Throwable exceptionOriginal, String packageName, int showLines) {
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
            for (int i = 1; i < arrs.length; i++) {
                String temp = arrs[i];
                if (temp != null && temp.indexOf(packageName) > 0) {
                    sbuf.append(temp + "\n");
                }
            }
        }


        return sbuf.toString();
    }
}

