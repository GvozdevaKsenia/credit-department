package com.gvozdeva.creditdepartment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultant {

    private Integer id;
    private Bank bank;
    private String firstName;
    private String surname;
}
