package com.example.demo.webConfig.errorHandler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import sun.rmi.runtime.Log;


@ControllerAdvice
public class ResponseAdvisorObject implements ResponseBodyAdvice<Object>{


    @Override
    public boolean supports (
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ){
        String requestPath = request.getURI().getPath();

//        System.out.println(body.getClass());

        if (body instanceof ApiError){
            return new ApiErrorResponse(body);
        }

        if (body instanceof ApiSuccessResponse) {
            return body;
        }

        if (requestPath.startsWith("/api")) {

            // http://www.voidcn.com/article/p-pzmzleow-bou.html

            return new ApiSuccessResponse(body);
        }

        return body;
    }

}
