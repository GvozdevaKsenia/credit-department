package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.model.entity.Bank;
import com.gvozdeva.creditdepartment2.util.ConnectionManager;
import com.gvozdeva.creditdepartment2.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankDao implements Dao<Integer, Bank> {

    private static final BankDao INSTANCE = new BankDao();

    private static final String SAVE_SQL = """
            INSERT INTO credit.bank (name, address, telephone, email)
            VALUES (?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM credit.bank
            WHERE id = ?
            """;

    private static final String DELETE_BY_TITLE_SQL = """
            DELETE FROM credit.bank
            WHERE name = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE credit.bank
            SET name = ?,
            address = ?,
            telephone = ?,
            email = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, 
                name, 
                address, 
                telephone,
                email
            FROM credit.bank 
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?         
            """;

    @Override
    public Bank save(Bank entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getAddress());
            preparedStatement.setObject(3, entity.getTelephone());
            preparedStatement.setObject(4, entity.getEmail());

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

    public boolean delete(String name) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_BY_TITLE_SQL)) {
            preparedStatement.setObject(1, name);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Bank> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Bank> banks = new ArrayList<>();
            while (resultSet.next()) {
                banks.add(buildBank(resultSet));
            }
            return banks;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Bank> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Bank> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);

            var resultSet = preparedStatement.executeQuery();
            Bank bank = null;
            if (resultSet.next()) {
                bank = buildBank(resultSet);
            }
            return Optional.ofNullable(bank);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Bank entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getName());
            preparedStatement.setObject(3, entity.getAddress());
            preparedStatement.setObject(4, entity.getTelephone());
            preparedStatement.setObject(5, entity.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Bank buildBank(ResultSet resultSet) throws SQLException {
        return new Bank(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("telephone"),
                resultSet.getString("email")
        );
    }

    public static BankDao getInstance() {
        return INSTANCE;
    }
}
