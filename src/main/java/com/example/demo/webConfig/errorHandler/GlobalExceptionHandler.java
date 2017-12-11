package com.example.demo.webConfig.errorHandler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(Exception ex, HttpServletRequest request) throws IOException {

        logger.error(" \n ==================== 404 API Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 5, ""));
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");

        return mav;
    }






    @ExceptionHandler(value = { Exception.class })
    public ModelAndView defaultErrorHandler ( HttpServletRequest request, Exception ex) {


        logger.error(" \n ==================== 500 Page Error : " + ex.getMessage() + "\n ===== Request Url: " + Util.makeUrl(request) + "\n" + Util.stackTraceToString(ex, 5, ""));

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");

        return mav;
    }


}
