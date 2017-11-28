package com.example.demo.interceptor;

import com.example.demo.customReturnValueHandler.ResultResponseHandlerMethodProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DemoWebMVCConfig extends WebMvcConfigurerAdapter {


    @Autowired
    private DemoRequestInterceptor demoInterceptor ;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(demoInterceptor);
    }


//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//
//        super.configureMessageConverters(converters);
//        converters.add(0, new MappingJackson2HttpMessageConverter());
//    }



    @Override
    public void addReturnValueHandlers(final List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        returnValueHandlers.add(new ResultResponseHandlerMethodProcessor(messageConverters));
    }
}
