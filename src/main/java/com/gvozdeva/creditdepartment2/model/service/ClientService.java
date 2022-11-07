package com.gvozdeva.creditdepartment2.model.service;

import com.gvozdeva.creditdepartment2.controller.dto.CreateClientDto;
import com.gvozdeva.creditdepartment2.controller.mapper.ClientMapper;
import com.gvozdeva.creditdepartment2.controller.mapper.CreateClientMapper;
import com.gvozdeva.creditdepartment2.exception.ValidationException;
import com.gvozdeva.creditdepartment2.model.dao.ClientDao;
import com.gvozdeva.creditdepartment2.model.entity.Client;
import com.gvozdeva.creditdepartment2.validator.CreateClientValidator;
import lombok.SneakyThrows;

import java.util.Optional;

public class ClientService {

    private static final ClientService INSTANCE = new ClientService();

    private final ClientDao clientDao = ClientDao.getInstance();
    private final ClientMapper clientMapper = ClientMapper.getInstance();
    private final CreateClientMapper createClientMapper = CreateClientMapper.getInstance();
    private final CreateClientValidator createClientValidator = CreateClientValidator.getInstance();

    public Optional<CreateClientDto> login(String email, String password) {
        return clientDao.findByEmailAndPassword(email, password)
                .map(clientMapper::mapFrom);
    }

    @SneakyThrows
    public Long create(CreateClientDto clientDto) {
        var validationResult = createClientValidator.isValid(clientDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var client = createClientMapper.mapFrom(clientDto);
        clientDao.save(client);
        return client.getId();
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }
}
