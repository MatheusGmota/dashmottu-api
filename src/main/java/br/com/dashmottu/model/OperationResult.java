package br.com.dashmottu.model;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationResult<T> {
    private T data;
    private int statusCode = 200;
    private String errorMessage;

    private OperationResult(T data, int statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    private OperationResult(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public static <T> OperationResult<T> success(T data) {
        return new OperationResult<>(data, 200);
    }

    public static <T> OperationResult<T> success(@Nullable T data,  int statusCode) {
        return new OperationResult<>(data, statusCode);
    }

    public static <T> OperationResult<T> failure(String errorMessage) {
        return new OperationResult<>(errorMessage, 400);
    }

    public static <T> OperationResult<T> failure(String errorMessage, int statusCode) {
        return new OperationResult<>(errorMessage, statusCode);
    }
}
