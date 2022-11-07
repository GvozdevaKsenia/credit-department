package com.gvozdeva.creditdepartment2.exception;

import java.sql.SQLException;

public class DaoException extends RuntimeException {

    private final String message;

    public DaoException(SQLException throwables) {
        message = throwables.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
