package com.gvozdeva.creditdepartment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    private Long id;
    private String firstName;
    private String surname;
    private LocalDate birthDate;
    private String telephone;
    private String passportNo;
    private String email;
    private String password;
}
