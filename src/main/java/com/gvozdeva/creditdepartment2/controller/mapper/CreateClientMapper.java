package com.gvozdeva.creditdepartment2.controller.mapper;

import com.gvozdeva.creditdepartment2.controller.dto.CreateClientDto;
import com.gvozdeva.creditdepartment2.model.entity.Client;
import com.gvozdeva.creditdepartment2.util.LocalDateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateClientMapper implements Mapper<CreateClientDto, Client> {

    private static final CreateClientMapper INSTANCE = new CreateClientMapper();

    @Override
    public Client mapFrom(CreateClientDto object) {
        return Client.builder()
                .firstName(object.getFirstName())
                .surname(object.getSurname())
                .birthDate(LocalDateFormatter.format(object.getBirthDate()))
                .telephone(object.getTelephone())
                .passportNo(object.getPassportNo())
                .email(object.getEmail())
                .password(object.getPassword())
                .build();
    }

    public static CreateClientMapper getInstance() {
        return INSTANCE;
    }
}
