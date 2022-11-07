package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.util.ConnectionManager;
import com.gvozdeva.creditdepartment2.model.entity.Consultant;
import com.gvozdeva.creditdepartment2.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultantDao implements Dao<Integer, Consultant> {

    private static final ConsultantDao INSTANCE = new ConsultantDao();

    private static final BankDao bankDao = BankDao.getInstance();

    private static final String SAVE_SQL = """
            INSERT INTO credit.consultant (bank_id, first_name, surname)
            VALUES (?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM credit.consultant
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE credit.consultant
            SET bank_id = ?,
            first_name = ?,
            surname = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, 
                bank_id, 
                first_name, 
                surname
            FROM credit.consultant 
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?         
            """;

    private static final String FIND_ALL_BY_BANK_ID_SQL = """
            SELECT c.id, c.bank_id, b.name, c.first_name, c.surname
            FROM credit.consultant c
            JOIN credit.bank b on c.bank_id = b.id
            WHERE bank_id = ?
            """;

    @Override
    public Consultant save(Consultant entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, entity.getBank().getId());
            preparedStatement.setObject(2, entity.getFirstName());
            preparedStatement.setObject(3, entity.getSurname());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }

            return entity;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Consultant> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Consultant> consultants = new ArrayList<>();
            while (resultSet.next()) {
                consultants.add(buildConsultant(resultSet));
            }
            return consultants;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Consultant> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Consultant> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);

            var resultSet = preparedStatement.executeQuery();
            Consultant consultant = null;
            if (resultSet.next()) {
                consultant = buildConsultant(resultSet);
            }
            return Optional.ofNullable(consultant);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Consultant entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getBank().getId());
            preparedStatement.setObject(3, entity.getFirstName());
            preparedStatement.setObject(4, entity.getSurname());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<Consultant> findAllByBankId(Integer bankId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_BANK_ID_SQL)) {
            preparedStatement.setObject(1, bankId);

            var resultSet = preparedStatement.executeQuery();
            List<Consultant> consultants = new ArrayList<>();
            while (resultSet.next()) {
                consultants.add(buildConsultant(resultSet));
            }
            return consultants;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Consultant buildConsultant(ResultSet resultSet) throws SQLException {
        return new Consultant(
                resultSet.getInt("id"),
                bankDao.findById(resultSet.getInt("bank_id")).orElse(null),
                resultSet.getString("first_name"),
                resultSet.getString("surname")
        );
    }

    public static ConsultantDao getInstance() {
        return INSTANCE;
    }
}
