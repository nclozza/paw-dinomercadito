package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.AddressDAO;
import ar.edu.itba.paw.interfaces.Services.AddressService;
import ar.edu.itba.paw.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;

    @Override
    public Address createAddress(final Integer userId, final String street, final Integer number, final String city,
                                 final String province, final String zipCode, final String country) {
        return addressDAO.createAddress(userId, street, number, city, province, zipCode, country);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<Address> findAddressByAddressId(final Integer addressId) {
        return addressDAO.findAddressByAddressId(addressId);
    }

    @Transactional (readOnly = true)
    @Override
    public List<Address> findAddressByUserId(final Integer userId) {
        return addressDAO.findAddressesByUserId(userId);
    }

    @Override
    public boolean deleteAddress(final Integer addressId) {
        return addressDAO.deleteAddressByAddressId(addressId);
    }

    @Override
    public Optional<Address> updateAddress(final String street, final Integer number, final String city, final String province,
                                 final String zipCode, final String country) {
        return Optional.empty();
    }
}
