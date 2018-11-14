package ar.edu.itba.paw.persistence.jdbc;

import ar.edu.itba.paw.interfaces.DAO.AddressDAO;
import ar.edu.itba.paw.models.Address;
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

public class AddressDaoJDBC implements AddressDAO {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressDaoJDBC.class);

    @Autowired
    public AddressDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("addresses")
                .usingGeneratedKeyColumns("addressid");
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

        LOGGER.info("Address inserted with addressId = {}", addressId.intValue());

        return new Address(addressId.intValue(), userId, street, number, city, province, zipCode, country);
    }

    public boolean deleteAddressByAddressId(final Integer addressId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM addresses WHERE addressid = ?",
                addressId);

        if (deletedRows == 1)
            LOGGER.info("Address deleted with addressId = {}", addressId);
        else
            LOGGER.info("Address not found with addressId = {}", addressId);

        return deletedRows == 1;
    }

    public boolean deleteAddressByUserId(final Integer userId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM addresses WHERE userid = ?",
                userId);

        if (deletedRows == 1)
            LOGGER.info("Address deleted with userId = {}", userId);
        else
            LOGGER.info("Address not found with userId = {}", userId);

        return deletedRows == 1;
    }

    public Optional<Address> findAddressByAddressId(final Integer addressId) {
        return jdbcTemplate.query("SELECT * FROM addresses WHERE addressid = ?",
                ROW_MAPPER, addressId).stream().findFirst();
    }

    public List<Address> findAddressesByUserId(final Integer userId) {
        final List<Address> addressList = jdbcTemplate.query("SELECT * FROM addresses WHERE userid = ?",
                ROW_MAPPER, userId);

        return addressList;
    }

    @Override
    public Optional<Address> updateAddress(final Integer addressId, final String street, final Integer number, final String city,
                                           final String province, final String zipCode, final String country) {
        jdbcTemplate.update("UPDATE addresses SET street = ?, number = ?, city = ?, " +
                        "province = ?, zipCode = ?, country = ? WHERE addressid = ?", street, number, city, province,
                zipCode, country, addressId);

        LOGGER.info("Address updated with addressId = {}", addressId);

        return findAddressByAddressId(addressId);
    }
}
