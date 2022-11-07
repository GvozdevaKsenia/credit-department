package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.util.ConnectionManager;
import com.gvozdeva.creditdepartment2.model.entity.CreditType;
import com.gvozdeva.creditdepartment2.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditTypeDao implements Dao<Integer, CreditType> {

    private static final CreditTypeDao INSTANCE = new CreditTypeDao();

    private static final String SAVE_SQL = """
            INSERT INTO credit.credit_type (name, description)
            VALUES (?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM credit.credit_type
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE credit.credit_type
            SET name = ?,
            description = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, 
                name, 
                description
            FROM credit.credit_type 
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?         
            """;

    @Override
    public CreditType save(CreditType entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getDescription());

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
    public List<CreditType> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<CreditType> types = new ArrayList<>();
            while (resultSet.next()) {
                types.add(buildCreditType(resultSet));
            }
            return types;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<CreditType> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<CreditType> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);

            var resultSet = preparedStatement.executeQuery();
            CreditType type = null;
            if (resultSet.next()) {
                type = buildCreditType(resultSet);
            }
            return Optional.ofNullable(type);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(CreditType entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getName());
            preparedStatement.setObject(3, entity.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private CreditType buildCreditType(ResultSet resultSet) throws SQLException {
        return new CreditType(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description")
        );
    }

    public static CreditTypeDao getInstance() {
        return INSTANCE;
    }
}
