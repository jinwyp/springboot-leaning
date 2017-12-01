package com.example.demo.webConfig.errorHandler;

public class ApiErrorResponse {



    private boolean status = false;
    private Object error;


    public ApiErrorResponse(Object err) {
        this.error = err;
    }

    public ApiErrorResponse(Boolean status, Object err) {
        this.status = status;
        this.error = err;
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }



}
