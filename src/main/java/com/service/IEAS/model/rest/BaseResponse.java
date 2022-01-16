package com.service.api.model.rest;

public class BaseResponse {

    private String status;
    private String timestamp;
    private String statusCode;
    private String message;
    private String error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BaseResponse{status=").append(status);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", statusCode=").append(statusCode);
        sb.append(", message=").append(message);
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }

}
