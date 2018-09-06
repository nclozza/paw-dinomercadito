package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.UserDAO;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
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
            resultSet.getDate("birthdate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
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
                           final LocalDate birthdate) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username); // la key es el nombre de la columna
        args.put("password", password);
        args.put("email", email);
        args.put("phone", phone);
        args.put("birthdate", birthdate.toString());

        final Number userId = jdbcInsert.executeAndReturnKey(args);

        return new User(userId.intValue(), username, password, email, phone, birthdate);
    }

    @Override
    public User findUserById(final Integer userId) {
        final List<User> usersList = jdbcTemplate.query("SELECT * FROM users WHERE userid = ?", ROW_MAPPER, userId);

        return usersList.get(0);
    }

    @Override
    public boolean deleteUser(final Integer userId) {
        final List<User> usersList = jdbcTemplate.query("DELETE * FROM users WHERE userid = ?", ROW_MAPPER, userId);

        return true;
    }

    @Override
    public boolean updateUserFunds(final Integer userId, final Double funds) {
        final List<User> usersList = jdbcTemplate.query("UPDATE users SET funds = ? WHERE userid = ?", ROW_MAPPER, funds, userId);

        return true;
    }
}
