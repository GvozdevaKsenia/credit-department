package com.gvozdeva.creditdepartment2.exception;

import com.gvozdeva.creditdepartment2.validator.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException {

    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
