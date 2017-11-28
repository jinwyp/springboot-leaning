package com.example.demo.error;

public class ApiErrorResponse {

    private boolean status = false;
    private int code;
    private String message;


    public ApiErrorResponse(int code) {
        this.code = code;
        this.message = message;
    }


    public ApiErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                ", code=" + code +
                ", message=" + message +
                '}';
    }

}
