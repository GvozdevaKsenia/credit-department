package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.exception.DaoException;
import com.gvozdeva.creditdepartment2.model.entity.Consultant;
import com.gvozdeva.creditdepartment2.model.entity.Consultation;
import com.gvozdeva.creditdepartment2.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConsultationDao implements Dao<Long, Consultation>  {

    private static final ConsultationDao INSTANCE = new ConsultationDao();

    private static final ClientDao clientDao = ClientDao.getInstance();

    private static final String SAVE_SQL = """
            INSERT INTO credit.consultation (fio, topic, email, telephone, message)
            VALUES (?, ?, ?, ?, ?)
            """;

    @Override
    public Consultation save(Consultation entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, entity.getClient().getId());
            preparedStatement.setObject(2, entity.getTopic());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getTelephone());
            preparedStatement.setObject(5, entity.getMessage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            }

            return entity;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Consultation> findAll() {
        return null;
    }

    @Override
    public Optional<Consultation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Consultation> findById(Long id, Connection connection) {
        return Optional.empty();
    }

    @Override
    public void update(Consultation entity) {

    }

    public static ConsultationDao getInstance() {
        return INSTANCE;
    }
}
