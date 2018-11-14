package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testAddresses.sql")
public class AddressesDaoHibernateTest {

    private static final String STREET = "Siempreviva";
    private static final Integer NUMBER = 1234;
    private static final String CITY = "Springfield";
    private static final String PROVINCE = "pvcia";
    private static final String ZIPCODE = "1234";
    private static final String COUNTRY = "Arg";

    private static final String STREETUPDATE = "New Blvd";
    private static final Integer NUMBERUPDATE = 1236;
    private static final String CITYUPDATE = "Shelbyville";
    private static final String PROVINCEUPDATE = "pvcia";
    private static final String ZIPCODEUPDATE = "5678";
    private static final String COUNTRYUPDATE = "nuevoPais";

    private static final Integer DUMMY_ADDR_ID = 8888;
    private static final Integer DUMMY_USER_ID = 9999;


    @Autowired
    private AddressDaoHibernate addressDao;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM addresses");
        em.flush();
    }

    @Test
    public void testAddressCreate() {
        final Address address = addressDao.createAddress(DUMMY_USER_ID, STREET, NUMBER, CITY, PROVINCE, ZIPCODE,
                COUNTRY);

        assertNotNull(address);
        assertEquals(STREET, address.getStreet());
        assertEquals(NUMBER, address.getNumber());
        assertEquals(CITY, address.getCity());
        assertEquals(PROVINCE, address.getProvince());
        assertEquals(ZIPCODE, address.getZipCode());
        assertEquals(COUNTRY, address.getCountry());
        assertTrue(em.contains(address));
    }

    @Test
    public void testAddressUpdate() {
        Optional<Address> address = addressDao.updateAddress(DUMMY_ADDR_ID, STREETUPDATE, NUMBERUPDATE, CITYUPDATE,
                PROVINCEUPDATE, ZIPCODEUPDATE, COUNTRYUPDATE);

        assertTrue(address.isPresent());
        assertEquals(STREETUPDATE, address.get().getStreet());
        assertEquals(NUMBERUPDATE, address.get().getNumber());
        assertEquals(CITYUPDATE, address.get().getCity());
        assertEquals(PROVINCEUPDATE, address.get().getProvince());
        assertEquals(ZIPCODEUPDATE, address.get().getZipCode());
        assertEquals(COUNTRYUPDATE, address.get().getCountry());
        assertTrue(em.contains(address.get()));
    }

    @Test
    public void testAddressFind() {
        Optional<Address> addressFound = addressDao.findAddressByAddressId(DUMMY_ADDR_ID);
        assertTrue(addressFound.isPresent());
        assertEquals(DUMMY_ADDR_ID, addressFound.get().getAddressId());
    }

    @Test
    public void testAddressDelete() {
        assertTrue(addressDao.deleteAddressByAddressId(DUMMY_ADDR_ID));
    }
}

