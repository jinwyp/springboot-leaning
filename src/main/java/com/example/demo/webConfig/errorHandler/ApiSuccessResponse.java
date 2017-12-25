package com.example.demo.webConfig.errorHandler;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ApiSuccessResponse <T>{

    private boolean status = true;

    private T data;


    public ApiSuccessResponse(T data) {
        this.data = data;
    }

    public ApiSuccessResponse(Boolean status, T data) {
        this.status = status;
        this.data = data;
    }


    public static final <M> ApiSuccessResponse<M> ok(M m) {
        return new ApiSuccessResponse(m);
    }


    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSON(Object object) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(new ApiSuccessResponse(object));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
