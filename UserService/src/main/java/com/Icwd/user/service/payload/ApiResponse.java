package com.Icwd.user.service.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private String message;
    private Boolean success;
    private HttpStatus status;

    // Private constructor to force usage of the builder
    private ApiResponse(ApiResponseBuilder builder) {
        this.message = builder.message;
        this.success = builder.success;
        this.status = builder.status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    // Static builder class
    public static class ApiResponseBuilder {
        private String message;
        private Boolean success;
        private HttpStatus status;

        public ApiResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder success(Boolean success) {
            this.success = success;
            return this;
        }

        public ApiResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }

    // Static method to create a new builder
    public static ApiResponseBuilder builder() {
        return new ApiResponseBuilder();
    }
}
