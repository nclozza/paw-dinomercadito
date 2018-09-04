package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PostDAO;
import ar.edu.itba.paw.interfaces.UserDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoJDBC implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    private final PostDAO postDaoJDBC;

    private final static RowMapper<User> ROW_MAPPER = (rs, rowNum) -> {
        LocalDate birthdate = LocalDate.parse(rs.getString("birthdate"));
        return new User(rs.getString("username"), rs.getString("password"),
                rs.getString("email"), rs.getString("phone"),
                rs.getString("address"), birthdate);
    };

    @Autowired
    public UserDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dinouser");
    }

    @Override
    public User createUser(final String username, final String password, final String email, final String phone, final String address, final LocalDate birthdate) {
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

    @Override
    public User findUserById(final Integer userId) {
        final List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE userId = ?", ROW_MAPPER, userId);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public boolean deleteUser(final Integer userId) {
        jdbcTemplate.query("DELETE * FROM users WHERE userId = ?", ROW_MAPPER, userId);
        // falta hacer la query para eliminar los posts de ese user
        return false;
    }

    @Override
    public boolean updateUser(String username, String password, String email, String phone, String address,
                              LocalDate birthdate) {
        return false;
    }

    @Override
    public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId) {
        return false;
    }

    @Override
    public Post postProduct(final Product product, final Double price, final Integer userId, final String description) {
        return postDaoJDBC.createPost(product, price, userId, description);
    }
}
