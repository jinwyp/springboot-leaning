package com.example.demo.webConfig.errorHandler;

public class ApiSuccessResponse {

    private boolean status = true;

    private Object data;

    public ApiSuccessResponse(Object data) {
        this.data = data;
    }

    public ApiSuccessResponse(Boolean status, Object data) {
        this.status = status;
        this.data = data;
    }


    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }


    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
