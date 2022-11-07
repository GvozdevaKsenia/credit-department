package com.gvozdeva.creditdepartment2.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderConsultationDto {

    String fio;
    String topic;
    String email;
    String telephone;
    String message;
}
