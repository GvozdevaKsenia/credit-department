package com.gvozdeva.creditdepartment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultation {

    private Long id;
    private Client client;
    private String topic;
    private String email;
    private String telephone;
    private String message;
}
