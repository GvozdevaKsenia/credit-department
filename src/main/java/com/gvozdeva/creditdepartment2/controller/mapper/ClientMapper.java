package com.gvozdeva.creditdepartment2.controller.mapper;

import com.gvozdeva.creditdepartment2.controller.dto.CreateClientDto;
import com.gvozdeva.creditdepartment2.model.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper implements Mapper<Client, CreateClientDto> {

    private static final ClientMapper INSTANCE = new ClientMapper();

    @Override
    public CreateClientDto mapFrom(Client object) {
        return CreateClientDto.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .surname(object.getSurname())
                .birthDate(object.getBirthDate().toString())
                .email(object.getEmail())
                .build();
    }

    public static ClientMapper getInstance() {
        return INSTANCE;
    }
}
