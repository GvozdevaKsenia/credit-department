package com.gvozdeva.creditdepartment2.controller.mapper;

import com.gvozdeva.creditdepartment2.controller.dto.BankDto;
import com.gvozdeva.creditdepartment2.model.entity.Bank;

public class BankCreationMapper implements Mapper<BankDto, Bank> {

    private static final BankCreationMapper INSTANCE = new BankCreationMapper();

    private BankCreationMapper(){
    }

    @Override
    public Bank mapFrom(BankDto object) {
        return Bank.builder()
                .name(object.getName())
                .address(object.getAddress())
                .telephone(object.getTelephone())
                .email(object.getEmail())
                .build();
    }

    public static BankCreationMapper getInstance() {
        return INSTANCE;
    }
}
