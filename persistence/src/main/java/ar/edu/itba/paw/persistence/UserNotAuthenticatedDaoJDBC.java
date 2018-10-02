package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.UserNotAuthenticatedDAO;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.UserNotAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
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

    public UserNotAuthenticated createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate, final String signUpDate, final Integer code) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username); // la key es el nombre de la columna
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

    public UserNotAuthenticated findUserByUsername(String username) {
        final List<UserNotAuthenticated> usersList = jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE username = ?", ROW_MAPPER, username);

        return usersList.get(0);
    }

    public UserNotAuthenticated findUserByUserId(final Integer userId) {
        final List<UserNotAuthenticated> usersList = jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE userid = ?", ROW_MAPPER, userId);

        return usersList.get(0);
    }

    public boolean deleteUser(final Integer userId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM usersNotAuthenticated WHERE userid = ?", userId);

        if (deletedRows == 1)
            LOGGER.info("User not authenticated deleted with userId = {}", userId);
        else
            LOGGER.info("User not authenticated not found with userId = {}", userId);

        return deletedRows == 1;
    }

    public UserNotAuthenticated findUserByCode(final Integer code){
        final List<UserNotAuthenticated> usersList = jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE code = ?",ROW_MAPPER, code);

        if (usersList.isEmpty())
            return null;

        return usersList.get(0);
    }

    public boolean checkCode(final Integer code){

        final List<UserNotAuthenticated> userList = jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE code = ?", ROW_MAPPER,code);

        if (userList.isEmpty())
            return true;
        else
            return false;
    }

    public boolean checkUsername(final String username){
        final List<UserNotAuthenticated> userList = jdbcTemplate.query("SELECT * FROM usersNotAuthenticated WHERE username = ?", ROW_MAPPER, username);

        if (userList.isEmpty())
            return true;
        else
            return false;
    }
}
