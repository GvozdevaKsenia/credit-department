package com.gvozdeva.creditdepartment2.controller.mapper;

import com.gvozdeva.creditdepartment2.model.entity.Bank;
import com.gvozdeva.creditdepartment2.controller.dto.BankDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankMapper implements Mapper<Bank, BankDto> {

    private static final BankMapper INSTANCE = new BankMapper();

    @Override
    public BankDto mapFrom(Bank object) {
        return BankDto.builder()
                .id(object.getId())
                .name(object.getName())
                .address(object.getAddress())
                .telephone(object.getTelephone())
                .email(object.getEmail())
                .build();
    }

    public static BankMapper getInstance() {
        return INSTANCE;
    }
}
