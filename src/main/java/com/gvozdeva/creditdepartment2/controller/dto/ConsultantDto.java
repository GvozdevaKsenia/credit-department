package com.gvozdeva.creditdepartment2.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConsultantDto {

    Integer id;
    String firstName;
    String surname;
}
