package com.gvozdeva.creditdepartment2.model.entity;

public enum CreditStatus {

    ACTIVE("Активный"),
    DEFRAYED("Погашен"),
    IN_REVIEW("В рассмотрении"),
    INDEBTED("Задолжен");

    private final String message;

    CreditStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
