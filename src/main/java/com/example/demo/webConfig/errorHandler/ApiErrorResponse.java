package com.example.demo.webConfig.errorHandler;

public class ApiErrorResponse {

    private boolean status = false;
    private int code;
    private String message;


    public ApiErrorResponse(int code) {
        this.code = code;
        this.message = "";
    }


    public ApiErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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


    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                ", code=" + code +
                ", message=" + message +
                '}';
    }

}
