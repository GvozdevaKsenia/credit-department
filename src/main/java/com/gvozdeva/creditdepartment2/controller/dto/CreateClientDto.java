package com.gvozdeva.creditdepartment2.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateClientDto {

    Long id;
    String firstName;
    String surname;
    String birthDate;
    String telephone;
    String passportNo;
    String email;
    String password;
}
