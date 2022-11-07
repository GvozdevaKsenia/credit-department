package com.gvozdeva.creditdepartment2.model.dao;

import com.gvozdeva.creditdepartment2.model.entity.Credit;
import com.gvozdeva.creditdepartment2.model.entity.CreditStatus;
import com.gvozdeva.creditdepartment2.exception.DaoException;
import com.gvozdeva.creditdepartment2.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditDao implements Dao<Long, Credit> {

    private static final CreditDao INSTANCE = new CreditDao();

    private final BankDao bankDao = BankDao.getInstance();
    private final ClientDao clientDao = ClientDao.getInstance();
    private final NonNaturalClientDao nonNaturalClientDao = NonNaturalClientDao.getInstance();
    private final GuarantorDao guarantorDao = GuarantorDao.getInstance();
    private final ConsultantDao consultantDao = ConsultantDao.getInstance();
    private final CreditTypeDao creditTypeDao = CreditTypeDao.getInstance();
    private final CurrencyDao currencyDao = CurrencyDao.getInstance();

    private static final String SAVE_SQL = """
            INSERT INTO credit.credit (bank_id,
            client_id,
            non_natural_client_id, 
            guarantor_id, 
            consultant_id, 
            type_id, 
            sum, 
            front_money, 
            interest_rate, 
            start_date, 
            end_date, 
            currency_id, 
            status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM credit.credit
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE credit.credit
            SET bank_id = ?,
            client_id = ?,
            non_natural_client_id = ?,
            guarantor_id = ?,
            consultant_id = ?,
            type_id = ?,
            sum = ?,
            front_money = ?,
            interest_rate = ?,
            start_date = ?,
            end_date = ?,
            currency_id = ?,
            status = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, 
            bank_id,
            client_id,
            non_natural_client_id,
            guarantor_id,
            consultant_id,
            type_id,
            sum,
            front_money,
            interest_rate,
            start_date,
            end_date,
            currency_id,
            status
            FROM credit.credit 
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?         
            """;

    private static final String FIND_BY_BANK_ID_SQL = """
            SELECT c.id,
                   c.bank_id,
                   b.name,
                   c.client_id,
                   c.non_natural_client_id,
                   c.guarantor_id,
                   c.consultant_id,
                   c.type_id,
                   c.sum,
                   c.front_money,
                   c.interest_rate,
                   c.start_date,
                   c.end_date,
                   c.currency_id,
                   c.status
            FROM credit.credit c
                    JOIN credit.bank b ON b.id = c.bank_id
            WHERE bank_id = ?;
            """;

    private static final String FIND_BY_CLIENT_ID_SQL = """
            SELECT c.id,
                   c.bank_id,
                   c.client_id,
                   cl.first_name,
                   cl.surname,
                   cl.passport_no,
                   c.non_natural_client_id,
                   c.guarantor_id,
                   c.consultant_id,
                   c.type_id,
                   c.sum,
                   c.front_money,
                   c.interest_rate,
                   c.start_date,
                   c.end_date,
                   c.currency_id,
                   c.status
            FROM credit.credit c
                    JOIN credit.client cl ON cl.id = c.client_id
            WHERE client_id = ?;
            """;

    private static final String FIND_BY_NON_NATURAL_CLIENT_ID_SQL = """
            
            """;

    @Override
    public Credit save(Credit entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            setNotNullFields(entity, preparedStatement);
            if (entity.getNonNaturalClient() == null) {
                preparedStatement.setObject(3, null);
            } else {
                preparedStatement.setObject(3, entity.getNonNaturalClient().getId());
            }

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
    public List<Credit> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Credit> credits = new ArrayList<>();
            while (resultSet.next()) {
                credits.add(buildCredit(resultSet));
            }
            return credits;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Credit> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Credit> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);

            var resultSet = preparedStatement.executeQuery();
            Credit credit = null;
            if (resultSet.next()) {
                credit = buildCredit(resultSet);
            }
            return Optional.ofNullable(credit);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<Credit> findAllByBankId(Integer bankId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_BANK_ID_SQL)) {
            preparedStatement.setObject(1, bankId);
            var resultSet = preparedStatement.executeQuery();
            List<Credit> credits = new ArrayList<>();
            while (resultSet.next()) {
                credits.add(buildCredit(resultSet));
            }
            return credits;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<Credit> findAllByClientId(Long clientId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_CLIENT_ID_SQL)) {
            preparedStatement.setObject(1, clientId);
            var resultSet = preparedStatement.executeQuery();
            List<Credit> credits = new ArrayList<>();
            while (resultSet.next()) {
                credits.add(buildCredit(resultSet));
            }
            return credits;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<Credit> findAllByNonNaturalClientId(Long nonNaturalClientId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_NON_NATURAL_CLIENT_ID_SQL)) {
            preparedStatement.setObject(1, nonNaturalClientId);
            var resultSet = preparedStatement.executeQuery();
            List<Credit> credits = new ArrayList<>();
            while (resultSet.next()) {
                credits.add(buildCredit(resultSet));
            }
            return credits;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Credit entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(14, entity.getId());
            setNotNullFields(entity, preparedStatement);
            if (entity.getNonNaturalClient() == null) {
                preparedStatement.setObject(3, null);
            } else {
                preparedStatement.setObject(3, entity.getNonNaturalClient().getId());
            }

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private void setNotNullFields(Credit entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setObject(1, entity.getBank().getId());
        preparedStatement.setObject(2, entity.getClient().getId());
        preparedStatement.setObject(4, entity.getGuarantor().getId());
        preparedStatement.setObject(5, entity.getConsultant().getId());
        preparedStatement.setObject(6, entity.getType().getId());
        preparedStatement.setObject(7, entity.getSum());
        preparedStatement.setObject(8, entity.getFrontMoney());
        preparedStatement.setObject(9, entity.getInterestRate());
        preparedStatement.setObject(10, entity.getStartDate());
        preparedStatement.setObject(11, entity.getEndDate());
        preparedStatement.setObject(12, entity.getCurrency().getId());
        preparedStatement.setObject(13, entity.getStatus().getMessage());
    }

    private Credit buildCredit(ResultSet resultSet) throws SQLException {
        return new Credit(
                resultSet.getLong("id"),
                bankDao.findById(resultSet.getInt("bank_id")).orElse(null),
                clientDao.findById(resultSet.getLong("client_id")).orElse(null),
                nonNaturalClientDao.findById(resultSet.getLong("non_natural_client_id")).orElse(null),
                guarantorDao.findById(resultSet.getLong("guarantor_id")).orElse(null),
                consultantDao.findById(resultSet.getInt("consultant_id")).orElse(null),
                creditTypeDao.findById(resultSet.getInt("type_id")).orElse(null),
                resultSet.getBigDecimal("sum"),
                resultSet.getBigDecimal("front_money"),
                resultSet.getInt("interest_rate"),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                currencyDao.findById(resultSet.getInt("currency_id")).orElse(null),
                CreditStatus.valueOf(resultSet.getString("status"))
        );
    }

    public static CreditDao getInstance() {
        return INSTANCE;
    }
}
