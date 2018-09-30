package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoJDBC implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

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
                           final String birthdate, final Double funds) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username); // la key es el nombre de la columna
        args.put("password", password);
        args.put("email", email);
        args.put("phone", phone);
        args.put("birthdate", birthdate);
        args.put("funds", funds);

        final Number userId = jdbcInsert.executeAndReturnKey(args);

        return new User(userId.intValue(), username, password, email, phone, birthdate, funds);
    }

    public User findUserByUsername(String username) {
        final List<User> usersList = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", ROW_MAPPER, username);

        return usersList.get(0);
    }

    public User findUserByUserId(final Integer userId) {
        final List<User> usersList = jdbcTemplate.query("SELECT * FROM users WHERE userid = ?", ROW_MAPPER, userId);

        return usersList.get(0);
    }

    public boolean deleteUser(final Integer userId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM users WHERE userid = ?", userId);

        return deletedRows == 1;
    }

    public User updateUser(final Integer userId, final String password, final String email,
                              final String phone, final String birthdate, final Double funds) {
        jdbcTemplate.update("UPDATE users SET password = ?, email = ?, phone = ?, birthdate = ?, funds = ? WHERE userId = ?",
                password, email, phone, birthdate, funds, userId);

        return findUserByUserId(userId);
    }

    public boolean checkUsername(final String username){
        final List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", ROW_MAPPER, username);

        if (userList.isEmpty())
            return true;
        else
            return false;
    }
}
