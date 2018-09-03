package ar.edu.itba.paw;

import ar.edu.itba.paw.interfaces.UserDAO;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoJDBC implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final static RowMapper<User> ROW_MAPPER = (rs, rowNum) -> {
        Date birthdate = new Date(new Integer(rs.getString("birthdate")));
        return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), birthdate);
    };

    @Autowired
    public UserDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dinousers")
                .usingGeneratedKeyColumns("username");
    }

    @Override
    public User findUserByUsername(final String username) {
        final List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", ROW_MAPPER, username);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public User createUser(final String username, final String password, final String email, final String phone, final String address, final Date birthdate) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username); // la key es el nombre de la columna
        args.put("password", password);
        args.put("email", email);
        args.put("phone", phone);
        args.put("address", address);
        args.put("birthdate", birthdate.toString());

        jdbcInsert.execute(args);

        return new User(username, password, email, phone, address, birthdate);
    }
}
