package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.util.ConnectionManager;
import com.gvozdeva.creditdepartment2.exception.DaoException;
import com.gvozdeva.creditdepartment2.model.entity.NonNaturalClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NonNaturalClientDao implements Dao<Long, NonNaturalClient> {

    private static final NonNaturalClientDao INSTANCE = new NonNaturalClientDao();

    private static final ClientDao clientDao = ClientDao.getInstance();

    private static final String SAVE_SQL = """
            INSERT INTO credit.non_natural_client (name, address, telephone, email, owner)
            VALUES (?, ?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM credit.non_natural_client
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE credit.non_natural_client
            SET name = ?,
            address = ?,
            telephone = ?,
            email = ?,
            owner = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, 
                name, 
                address, 
                telephone,
                email,
                owner
            FROM credit.non_natural_client 
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?         
            """;

    @Override
    public NonNaturalClient save(NonNaturalClient entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getAddress());
            preparedStatement.setObject(3, entity.getTelephone());
            preparedStatement.setObject(4, entity.getEmail());
            preparedStatement.setObject(5, entity.getOwner().getId());

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
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<NonNaturalClient> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<NonNaturalClient> nonNaturalClients = new ArrayList<>();
            while (resultSet.next()) {
                nonNaturalClients.add(buildNonNaturalClient(resultSet));
            }
            return nonNaturalClients;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<NonNaturalClient> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<NonNaturalClient> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);

            var resultSet = preparedStatement.executeQuery();
            NonNaturalClient nonNaturalClient = null;
            if (resultSet.next()) {
                nonNaturalClient = buildNonNaturalClient(resultSet);
            }
            return Optional.ofNullable(nonNaturalClient);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(NonNaturalClient entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getName());
            preparedStatement.setObject(3, entity.getAddress());
            preparedStatement.setObject(4, entity.getTelephone());
            preparedStatement.setObject(5, entity.getEmail());
            preparedStatement.setObject(6, entity.getOwner().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private NonNaturalClient buildNonNaturalClient(ResultSet resultSet) throws SQLException {
        return new NonNaturalClient(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("telephone"),
                resultSet.getString("email"),
                clientDao.findById(resultSet.getLong("owner")).orElse(null)
        );
    }

    public static NonNaturalClientDao getInstance() {
        return INSTANCE;
    }
}
