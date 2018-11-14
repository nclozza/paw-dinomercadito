package ar.edu.itba.paw.persistence.jdbc;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDaoJDBC implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJDBC.class);

    private final static RowMapper<User> ROW_MAPPER = (resultSet, rowNum) -> new User(
            resultSet.getInt("userid"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("email"),
            resultSet.getString("phone"),
            resultSet.getString("birthdate"),
            resultSet.getDouble("funds")
    );

    @Autowired
    public UserDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("userid");
    }

    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username); // la key es el nombre de la columna
        args.put("password", password);
        args.put("email", email);
        args.put("phone", phone);
        args.put("birthdate", birthdate);

        final Number userId = jdbcInsert.executeAndReturnKey(args);

        LOGGER.info("User inserted with userId = {}", userId.intValue());

        return new User(userId.intValue(), username, password, email, phone, birthdate);
    }

    public Optional<User> findUserByUsername(final String username) {
        return jdbcTemplate.query("SELECT * FROM users WHERE username = ?", ROW_MAPPER, username)
                .stream().findFirst();
    }

    public Optional<User> findUserByUserId(final Integer userId) {
        return jdbcTemplate.query("SELECT * FROM users WHERE userid = ?", ROW_MAPPER, userId)
                .stream().findFirst();
    }

    public boolean deleteUser(final Integer userId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM users WHERE userid = ?", userId);

        if (deletedRows == 1)
            LOGGER.info("User deleted with userId = {}", userId);
        else
            LOGGER.info("User not found with userId = {}", userId);

        return deletedRows == 1;
    }

    public Optional<User> updateUser(final Integer userId, final String password, final String email, final String phone,
                           final String birthdate) {
        jdbcTemplate.update("UPDATE users SET password = ?, email = ?, phone = ?, birthdate = ?, funds = ? WHERE userId = ?",
                password, email, phone, birthdate, userId);

        LOGGER.info("User updated with userId = {}", userId);

        return findUserByUserId(userId);
    }

    public boolean checkUsername(final String username) {
        final List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", ROW_MAPPER, username);

        return userList.isEmpty();
    }

    @Override
    public void addRating(User userId, Double rating) {

    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }

//    @Override
//    public boolean addFundsToUserId(final Double funds, final Integer userId) {
//        final Integer updatedRows = jdbcTemplate.update("UPDATE users SET funds = ? WHERE userId = ?", funds, userId);
//
//        return updatedRows == 1;
//    }
}
