package com.gvozdeva.creditdepartment2.controller.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
