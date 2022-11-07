package com.gvozdeva.creditdepartment2.model.service;

import com.gvozdeva.creditdepartment2.controller.mapper.ConsultantMapper;
import com.gvozdeva.creditdepartment2.model.dao.ConsultantDao;
import com.gvozdeva.creditdepartment2.controller.dto.ConsultantDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsultantService {

    private static final ConsultantService INSTANCE = new ConsultantService();

    private final ConsultantDao consultantDao = ConsultantDao.getInstance();
    private final ConsultantMapper consultantMapper = ConsultantMapper.getInstance();

    private ConsultantService(){
    }

    public List<ConsultantDto> findAll() {
        return consultantDao.findAll().stream()
                .map(consultantMapper::mapFrom)
                .collect(toList());
    }

    public List<ConsultantDto> findAllByBankId(Integer id) {
        return consultantDao.findAllByBankId(id).stream()
                .map(consultantMapper::mapFrom)
                .collect(toList());
    }

    public static ConsultantService getInstance() {
        return INSTANCE;
    }
}
