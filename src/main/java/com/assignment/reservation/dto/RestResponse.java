package com.assignment.reservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
public class RestResponse<T> {

    private T data;
    private String message;
    private List<Error> error;

    private RestResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    private RestResponse(List<Error> errors) {
        this.error = errors;
    }

    public static <T> RestResponse<T> success(T data, String message) {
        return new RestResponse<T>(data, message);
    }

    public static <T> RestResponse<T> success(T data) {
        return success(data, "");
    }

    public static <T> RestResponse<T> success() {
        return success(null, "");
    }

    public static ErrorResponseBuilder error(String field, String errorMessage) {
        return new ErrorResponseBuilder(new Error(field, errorMessage));
    }

    public static ErrorResponseBuilder error(String errorMessage) {
        return error(null, errorMessage);
    }

    public static ErrorResponseBuilder error() {
        return error(null, null);
    }

    @NoArgsConstructor
    public static class ErrorResponseBuilder {
        private List<Error> errors = new ArrayList<>();

        private ErrorResponseBuilder(Error error) {
            errors.add(error);
        }

        public ErrorResponseBuilder appendError(String field, String errorMessage) {
            errors.add(new Error(field, errorMessage));
            return this;
        }

        public ErrorResponseBuilder appendError(String errorMessage) {
            return this.appendError(null, errorMessage);
        }


        public RestResponse<?> build() {
            return new RestResponse<>(this.errors);
        }
    }


    @Getter
    @NoArgsConstructor
    public static class Error {
        private String field;
        private String message;

        public Error(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
