package com.example.demo.customReturnValueHandler;

import com.example.demo.error.ApiSuccessResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.List;

public class ResultResponseHandlerMethodProcessor extends RequestResponseBodyMethodProcessor {
    public ResultResponseHandlerMethodProcessor(final List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }

    public ResultResponseHandlerMethodProcessor(final List<HttpMessageConverter<?>> messageConverters, final ContentNegotiationManager contentNegotiationManager) {
        super(messageConverters, contentNegotiationManager);
    }



    @Override
    public boolean supportsReturnType(final MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class)) || true;
    }

    @Override
    public void handleReturnValue(
            final Object returnValue,
            final MethodParameter returnType,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        ApiSuccessResponse result = new ApiSuccessResponse(returnValue);
        super.handleReturnValue(result, returnType, mavContainer, webRequest);
    }
}