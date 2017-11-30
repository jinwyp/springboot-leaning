package com.example.demo.webConfig.customReturnValueHandler;

import com.example.demo.webConfig.errorHandler.ApiSuccessResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler handlerMethodReturnValueHandler;


    public ResponseBodyWrapHandler (HandlerMethodReturnValueHandler delegate) {
        this.handlerMethodReturnValueHandler = delegate;
    }

    @Override
    public boolean supportsReturnType (MethodParameter returnType) {
        return handlerMethodReturnValueHandler.supportsReturnType(returnType);
    }


    @Override
    public void handleReturnValue (
            Object returnValue,
            MethodParameter returnType,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest webRequest
    ) throws Exception {
        ApiSuccessResponse result = new ApiSuccessResponse(returnValue);
        this.handlerMethodReturnValueHandler.handleReturnValue(result, returnType, modelAndViewContainer, webRequest);

    }
}
