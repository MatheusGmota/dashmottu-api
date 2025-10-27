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
    public T data;
    public int statusCode = 200;
    public String errorMessage;

    private OperationResult(T data, int statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    private OperationResult(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public OperationResult<T> success(T data) {
        return new OperationResult<>(data, 200);
    }

    public OperationResult<T> success(@Nullable T data,  int statusCode) {
        return new OperationResult<>(data, statusCode);
    }

    public OperationResult<T> failure(String errorMessage) {
        return new OperationResult<>(errorMessage, 400);
    }

    public OperationResult<T> failure(String errorMessage, int statusCode) {
        return new OperationResult<>(errorMessage, statusCode);
    }
}
