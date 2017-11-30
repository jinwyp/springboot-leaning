package com.example.demo.webConfig.businessException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

    private  int code;
    private  String message;
    private  String field;

    @JsonCreator
    public ErrorMessage(
            @JsonProperty("code") int code,
            @JsonProperty("message") String message,
            @JsonProperty("field") String field) {

        this.code = code;
        this.message = message;
        this.field = field;
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
}
