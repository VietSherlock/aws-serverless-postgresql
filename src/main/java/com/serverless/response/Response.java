package com.serverless.response;

public class Response {

    private Object data;
    private int statusCode;

    public Response(Object data, int statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
