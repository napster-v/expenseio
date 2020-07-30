package com.expense.io.renderers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse<T> {
    private T error;

    public ErrorResponse(T error) {
        this.error = error;
    }
}
