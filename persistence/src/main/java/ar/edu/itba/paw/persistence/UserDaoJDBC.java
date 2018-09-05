package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PostDAO;
import ar.edu.itba.paw.interfaces.UserDAO;
import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoJDBC implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    /*
    private final static RowMapper<User> ROW_MAPPER = (rs, rowNum) -> {
        LocalDate birthdate = LocalDate.parse(rs.getString("birthdate"));
        return new User(rs.getString("username"), rs.getString("password"),
                rs.getString("email"), rs.getString("phone"),
                rs.getString("address"), birthdate);
    };
    */

    @Autowired
    public UserDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dinouser");
    }

    public User createUser(final String username, final String password, final String email, final String phone,
                           final Integer addressId, final LocalDate birthdate) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username); // la key es el nombre de la columna
        args.put("password", password);
        args.put("email", email);
        args.put("phone", phone);
        args.put("addressId", addressId);
        args.put("birthdate", birthdate.toString());

        jdbcInsert.execute(args);

        return new User(username, password, email, phone, addressId, birthdate);
    }

    @Override
    public User findUserById(final Integer userId) {
        final List<User> usersList = (List<User>) jdbcTemplate.query("SELECT * FROM users WHERE userId = ?",
            new RowMapper<User>() {
                @Override
                public User mapRow(final ResultSet resultSet, int i) throws SQLException {
                    return new User(resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getString("phone"), resultSet.getInt("addressId"),
                            resultSet.getDate("birthdate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                            resultSet.getDouble("funds"));
                }
            }, userId);

        return usersList.get(0);
    }

    @Override
    public boolean deleteUser(final Integer userId) {
        final List<User> usersList = (List<User>) jdbcTemplate.query("DELETE * FROM users WHERE userId = ?",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(final ResultSet resultSet, int i) throws SQLException {
                        return new User(resultSet.getString("username"),
                                resultSet.getString("password"), resultSet.getString("email"),
                                resultSet.getString("phone"), resultSet.getInt("addressId"),
                                resultSet.getDate("birthdate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                resultSet.getDouble("funds"));
                    }
                }, userId);

        return true;
    }

    @Override
    public boolean updateUserByFunds(final Integer userId, final Double funds) {
        final List<User> usersList = (List<User>) jdbcTemplate.query("UPDATE users SET funds = ? WHERE userId = ?",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(final ResultSet resultSet, int i) throws SQLException {
                        return new User(resultSet.getString("username"),
                                resultSet.getString("password"), resultSet.getString("email"),
                                resultSet.getString("phone"), resultSet.getInt("addressId"),
                                resultSet.getDate("birthdate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                resultSet.getDouble("funds"));
                    }
                }, funds, userId);

        return true;
    }

    public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId) {
        return false;
    }
}
