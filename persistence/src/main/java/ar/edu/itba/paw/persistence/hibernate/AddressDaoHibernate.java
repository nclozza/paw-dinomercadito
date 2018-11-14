package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.DAO.AddressDAO;
import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.User;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoHibernate implements AddressDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Address createAddress(Integer userId, String street, Integer number, String city, String province, String zipCode, String country) {
        User user = em.find(User.class, userId);
        Address address = new Address(user, street, number, city, province, zipCode, country);
        em.persist(address);
        LOGGER.info("Address inserted with addressId = {}", address.getAddressId().intValue());
        return address;
    }

    @Transactional
    @Override
    public boolean deleteAddressByAddressId(Integer addressId) {
        Address address = em.find(Address.class, addressId);

        if (address != null){
            em.remove(address);
            LOGGER.info("Address deleted with addressId = {}", addressId);

            return true;
        }
        LOGGER.info("Address not found with addressId = {}", addressId);

        return false;
    }

    @Transactional
    @Override
    public boolean deleteAddressByUserId(Integer userId) {
        User user = em.find(User.class, userId);
        List<Address> addresses = user.getAddressesList();

        if (!addresses.isEmpty()){
            for(Address a : addresses){
                em.remove(a);
            }
            LOGGER.info("Address deleted with userId = {}", userId);
            return true;
        }
        LOGGER.info("Address not found with userId = {}", userId);

        return false;
    }

    @Transactional
    @Override
    public Optional<Address> updateAddress(Integer addressId, String street, Integer number, String city, String province, String zipCode, String country) {
        Address address = em.find(Address.class, addressId);

        if(address != null){
            address.setStreet(street);
            address.setNumber(number);
            address.setCity(city);
            address.setProvince(province);
            address.setZipCode(zipCode);
            address.setCountry(country);
            em.merge(address);
            LOGGER.info("Address updated with addressId = {}", addressId);
        } else {
            LOGGER.info("Address not found with addressId = {}", addressId);
        }
        return Optional.ofNullable(address);
    }

    @Override
    public Optional<Address> findAddressByAddressId(Integer addressId) {
        return Optional.ofNullable(em.find(Address.class, addressId));
    }

    @Transactional
    @Override
    public List<Address> findAddressesByUserId(Integer userId) {
        User user = em.find(User.class, userId);
        Hibernate.initialize(user.getAddressesList());
        return user.getAddressesList();
    }
}
