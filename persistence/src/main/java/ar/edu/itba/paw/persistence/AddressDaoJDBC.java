package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.AddressDAO;
import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AddressDaoJDBC implements AddressDAO {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public AddressDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("posts")
                .usingGeneratedKeyColumns("postid");
    }

    private final static RowMapper<Address> ROW_MAPPER = (resultSet, rowNum) -> new Address(
            resultSet.getInt("addressid"),
            resultSet.getInt("userid"),
            resultSet.getString("street"),
            resultSet.getInt("number"),
            resultSet.getString("city"),
            resultSet.getString("province"),
            resultSet.getString("zipcode"),
            resultSet.getString("country")
    );


    @Override
    public Address createAddress(final Integer userId, final String street, final Integer number, final String city,
                                 final String province, final String zipCode, final String country) {
        final Map<String, Object> args = new HashMap<>();
        args.put("userid", userId);
        args.put("street", street);
        args.put("number", number);
        args.put("city", city);
        args.put("province", province);
        args.put("zipcode", zipCode);
        args.put("country", country);

        final Number addressId = jdbcInsert.executeAndReturnKey(args);

        return new Address(addressId.intValue(), userId, street, number, city, province, zipCode, country);
    }

    @Override
    public boolean deleteAddress(Integer addressId) {
        return false;
    }

    @Override
    public Address findAddressByAddressId(Integer addressId) {
        return null;
    }

    @Override
    public Address updateAddress(String street, Integer number, String city, String province, String zipCode, String country) {
        return null;
    }
}
