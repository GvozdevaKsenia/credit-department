package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.model.entity.Client;
import com.gvozdeva.creditdepartment2.util.ConnectionManager;
import com.gvozdeva.creditdepartment2.exception.DaoException;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao<Long, Client> {

    private static final ClientDao INSTANCE = new ClientDao();

    private static final String SAVE_SQL = """
            INSERT INTO credit.client (first_name, surname, birth_date, telephone, passport_no, email, password)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT id, first_name, surname, birth_date, telephone, passport_no, email, password
            FROM credit.client
            WHERE email = ? AND password = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM credit.client
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE credit.client
            SET first_name = ?,
            surname = ?,
            birth_date = ?,
            telephone = ?,
            passport_no = ?,
            email = ?,
            password = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, 
                first_name, 
                surname, 
                birth_date,
                telephone,
                passport_no,
                email
            FROM credit.client 
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?         
            """;

    private static final String FIND_BY_FIO_SQL = """
            SELECT c.id, c.first_name, c.surname, c.birth_date, c.telephone, c.passport_no, c.email, c.password
            FROM credit.client c
            WHERE c.first_name = ? AND c.surname = ?;
            """;


    @Override
    public Client save(Client entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, entity.getFirstName());
            preparedStatement.setObject(2, entity.getSurname());
            preparedStatement.setObject(3, entity.getBirthDate());
            preparedStatement.setObject(4, entity.getTelephone());
            preparedStatement.setObject(5, entity.getPassportNo());
            preparedStatement.setObject(6, entity.getEmail());
            preparedStatement.setObject(7, entity.getPassword());

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

    @SneakyThrows
    public Optional<Client> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            var resultSet = preparedStatement.executeQuery();
            Client client = null;
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            return Optional.ofNullable(client);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Client> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Client> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Client> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);

            var resultSet = preparedStatement.executeQuery();
            Client client = null;
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            return Optional.ofNullable(client);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Client> findByFio(String fio) {
        try (var connection = ConnectionManager.get()) {
            return findByFio(fio, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Client> findByFio(String fio, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_FIO_SQL)) {
            var nameAndSurname = fio.split(" ");
            preparedStatement.setObject(1, nameAndSurname[0]);
            preparedStatement.setObject(2, nameAndSurname[1]);

            var resultSet = preparedStatement.executeQuery();
            Client client = null;
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            return Optional.ofNullable(client);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Client entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getFirstName());
            preparedStatement.setObject(3, entity.getSurname());
            preparedStatement.setObject(4, entity.getBirthDate());
            preparedStatement.setObject(5, entity.getTelephone());
            preparedStatement.setObject(6, entity.getPassportNo());
            preparedStatement.setObject(7, entity.getEmail());
            preparedStatement.setObject(8, entity.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("surname"),
                resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getString("telephone"),
                resultSet.getString("passport_no"),
                resultSet.getString("email"),
                resultSet.getString("password")
        );
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
