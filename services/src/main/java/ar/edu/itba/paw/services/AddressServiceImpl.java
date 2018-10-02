package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.AddressDAO;
import ar.edu.itba.paw.interfaces.Services.AddressService;
import ar.edu.itba.paw.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;

    public Address createAddress(final Integer userId, final String street, final Integer number, final String city,
                                 final String province, final String zipCode, final String country) {
        return addressDAO.createAddress(userId, street, number, city, province, zipCode, country);
    }

    @Transactional (readOnly = true)
    public Address findAddressByAddressId(final Integer addressId) {
        return addressDAO.findAddressByAddressId(addressId);
    }

    @Transactional (readOnly = true)
    public List<Address> findAddressByUserId(final Integer userId) {
        return addressDAO.findAddressesByUserId(userId);
    }

    public boolean deleteAddress(final Integer addressId) {
        return addressDAO.deleteAddressByAddressId(addressId);
    }

    public Address updateAddress(final String street, final Integer number, final String city, final String province,
                                 final String zipCode, final String country) {
        return null;
    }
}
