package ar.edu.itba.paw.persistence.jdbc;

import ar.edu.itba.paw.interfaces.DAO.UserNotAuthenticatedDAO;
import ar.edu.itba.paw.models.UserNotAuthenticated;
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


public class UserNotAuthenticatedDaoJDBC implements UserNotAuthenticatedDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserNotAuthenticatedDaoJDBC.class);

    private final static RowMapper<UserNotAuthenticated> ROW_MAPPER = (resultSet, rowNum) -> new UserNotAuthenticated(
            resultSet.getInt("userid"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("email"),
            resultSet.getString("phone"),
            resultSet.getString("birthdate"),
            resultSet.getString("signUpDate"),
            resultSet.getInt("code")
    );

    @Autowired
    public UserNotAuthenticatedDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("usersnotauthenticated")
                .usingGeneratedKeyColumns("userid");
    }

    public UserNotAuthenticated createUser(final String username, final String password, final String email,
                                           final String phone, final String birthdate, final String signUpDate,
                                           final Integer code) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username);
        args.put("password", password);
        args.put("email", email);
        args.put("phone", phone);
        args.put("birthdate", birthdate);
        args.put("signUpDate", signUpDate);
        args.put("code", code);

        final Number userId = jdbcInsert.executeAndReturnKey(args);

        LOGGER.info("User not authenticated inserted with userId = {} and code = {}", userId.intValue(), code);

        return new UserNotAuthenticated(userId.intValue(), username, password, email, phone, birthdate, signUpDate, code);
    }

    public Optional<UserNotAuthenticated> findUserByUsername(final String username) {
        return jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE username = ?",
                ROW_MAPPER, username).stream().findFirst();
    }

    public Optional<UserNotAuthenticated> findUserByUserId(final Integer userId) {
        return jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE userid = ?",
                ROW_MAPPER, userId).stream().findFirst();
    }

    public boolean deleteUser(final Integer userId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM usersNotAuthenticated WHERE userid = ?", userId);

        if (deletedRows == 1)
            LOGGER.info("User not authenticated deleted with userId = {}", userId);
        else
            LOGGER.info("User not authenticated not found with userId = {}", userId);

        return deletedRows == 1;
    }

    public Optional<UserNotAuthenticated> findUserByCode(final Integer code) {
        return jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE code = ?",
                ROW_MAPPER, code).stream().findFirst();
    }

    public boolean checkCode(final Integer code) {

        final List<UserNotAuthenticated> userList = jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE code = ?",
                ROW_MAPPER, code);

        return userList.isEmpty();
    }

    public boolean checkUsername(final String username) {
        final List<UserNotAuthenticated> userList = jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE username = ?",
                ROW_MAPPER, username);

        return userList.isEmpty();
    }

    @Override
    public Optional<UserNotAuthenticated> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }
}
