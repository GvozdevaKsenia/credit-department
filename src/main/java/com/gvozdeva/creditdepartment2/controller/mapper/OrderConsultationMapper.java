package com.gvozdeva.creditdepartment2.controller.mapper;

import com.gvozdeva.creditdepartment2.controller.dto.OrderConsultationDto;
import com.gvozdeva.creditdepartment2.model.dao.ClientDao;
import com.gvozdeva.creditdepartment2.model.entity.Client;
import com.gvozdeva.creditdepartment2.model.entity.Consultation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConsultationMapper implements Mapper<OrderConsultationDto, Consultation> {

    private static final OrderConsultationMapper INSTANCE = new OrderConsultationMapper();

    private static final ClientDao clientDao = ClientDao.getInstance();

    @Override
    public Consultation mapFrom(OrderConsultationDto object) {
//        var client = clientDao.findByFio(object.getFio()).orElse(null);
        return Consultation.builder()
                .client(clientDao.findByFio(object.getFio()).orElse(null))
                .topic(object.getTopic())
                .email(object.getEmail())
                .telephone(object.getTelephone())
                .message(object.getMessage())
                .build();
    }

    public static OrderConsultationMapper getInstance() {
        return INSTANCE;
    }
}
