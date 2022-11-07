package com.gvozdeva.creditdepartment2.model.service;

import com.gvozdeva.creditdepartment2.controller.dto.ConsultantDto;
import com.gvozdeva.creditdepartment2.controller.dto.OrderConsultationDto;
import com.gvozdeva.creditdepartment2.controller.mapper.ConsultantMapper;
import com.gvozdeva.creditdepartment2.controller.mapper.OrderConsultationMapper;
import com.gvozdeva.creditdepartment2.model.dao.ConsultantDao;
import com.gvozdeva.creditdepartment2.model.dao.ConsultationDao;
import com.gvozdeva.creditdepartment2.model.entity.Consultation;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ConsultationService {

    private static final ConsultationService INSTANCE = new ConsultationService();

    private final ConsultationDao consultationDao = ConsultationDao.getInstance();
    private final OrderConsultationMapper mapper = OrderConsultationMapper.getInstance();

    private ConsultationService() {
    }

    public Long save(OrderConsultationDto consultationDto) {
        var consultation = mapper.mapFrom(consultationDto);
        consultationDao.save(consultation);
        return consultation.getId();
    }

    public static ConsultationService getInstance() {
        return INSTANCE;
    }
}
