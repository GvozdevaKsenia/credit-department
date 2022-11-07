package com.gvozdeva.creditdepartment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NonNaturalClient {

    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String email;
    private Client owner;
}
