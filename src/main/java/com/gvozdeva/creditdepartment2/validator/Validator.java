package com.gvozdeva.creditdepartment2.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
