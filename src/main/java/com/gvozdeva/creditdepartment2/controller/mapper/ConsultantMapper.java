package com.gvozdeva.creditdepartment2.controller.mapper;

import com.gvozdeva.creditdepartment2.model.entity.Consultant;
import com.gvozdeva.creditdepartment2.controller.dto.ConsultantDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultantMapper implements Mapper<Consultant, ConsultantDto> {

    private static final ConsultantMapper INSTANCE = new ConsultantMapper();

    @Override
    public ConsultantDto mapFrom(Consultant object) {
        return ConsultantDto.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .surname(object.getSurname())
                .build();
    }

    public static ConsultantMapper getInstance() {
        return INSTANCE;
    }
}
