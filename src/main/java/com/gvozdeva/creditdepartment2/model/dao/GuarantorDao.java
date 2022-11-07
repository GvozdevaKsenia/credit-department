package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.util.ConnectionManager;
import com.gvozdeva.creditdepartment2.exception.DaoException;
import com.gvozdeva.creditdepartment2.model.entity.Guarantor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuarantorDao implements Dao<Long, Guarantor> {

    private static final GuarantorDao INSTANCE = new GuarantorDao();

    private static final String SAVE_SQL = """
            INSERT INTO credit.guarantor (first_name, surname, telephone, passport_no)
            VALUES (?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM credit.guarantor
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE credit.guarantor
            SET first_name = ?,
            surname = ?,
            telephone = ?,
            passport_no = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, 
                first_name, 
                surname, 
                telephone,
                passport_no
            FROM credit.guarantor 
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?         
            """;

    @Override
    public Guarantor save(Guarantor entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, entity.getFirstName());
            preparedStatement.setObject(2, entity.getSurname());
            preparedStatement.setObject(3, entity.getTelephone());
            preparedStatement.setObject(4, entity.getPassportNo());

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
    public List<Guarantor> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Guarantor> guarantors = new ArrayList<>();
            while (resultSet.next()) {
                guarantors.add(buildGuarantor(resultSet));
            }
            return guarantors;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Guarantor> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Guarantor> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);

            var resultSet = preparedStatement.executeQuery();
            Guarantor guarantor = null;
            if (resultSet.next()) {
                guarantor = buildGuarantor(resultSet);
            }
            return Optional.ofNullable(guarantor);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Guarantor entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getFirstName());
            preparedStatement.setObject(3, entity.getSurname());
            preparedStatement.setObject(4, entity.getTelephone());
            preparedStatement.setObject(5, entity.getPassportNo());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Guarantor buildGuarantor(ResultSet resultSet) throws SQLException {
        return new Guarantor(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("surname"),
                resultSet.getString("telephone"),
                resultSet.getString("passport_no")
        );
    }

    public static GuarantorDao getInstance() {
        return INSTANCE;
    }
}
