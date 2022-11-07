package com.gvozdeva.creditdepartment2.model.service;

import com.gvozdeva.creditdepartment2.controller.mapper.BankCreationMapper;
import com.gvozdeva.creditdepartment2.controller.mapper.BankMapper;
import com.gvozdeva.creditdepartment2.model.dao.BankDao;
import com.gvozdeva.creditdepartment2.controller.dto.BankDto;
import com.gvozdeva.creditdepartment2.model.entity.Bank;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BankService {

    private static final BankService INSTANCE = new BankService();

    private final BankDao bankDao = BankDao.getInstance();
    private final BankMapper bankMapper = BankMapper.getInstance();
    private final BankCreationMapper bankCreationMapper = BankCreationMapper.getInstance();

    private BankService(){
    }

    public boolean delete(BankDto bankDto) {
        return bankDao.delete(bankDto.getName());
    }

    public Integer create(BankDto bankDto) {
        var bank = bankCreationMapper.mapFrom(bankDto);
        bankDao.save(bank);
        return bank.getId();
    }

    public List<BankDto> findAll() {
        return bankDao.findAll().stream()
                .map(bankMapper::mapFrom)
                .collect(toList());
    }

    public static BankService getInstance() {
        return INSTANCE;
    }
}
