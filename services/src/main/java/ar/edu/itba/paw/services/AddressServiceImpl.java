package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.AddressDAO;
import ar.edu.itba.paw.interfaces.AddressService;
import ar.edu.itba.paw.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;

    @Override
    public Address createAddress(final String street, final Integer number, final String city,
                                 final String province, final String zipCode, final String country) {
        return addressDAO.createAddress(street, number, city, province, zipCode, country);
    }

    @Override
    public Address findAddressByAddressId(final Integer addressId) {
        return null;
    }

    @Override
    public boolean deleteAddress(final Integer addressId) {
        return false;
    }

    @Override
    public Address updateAddress(final String street, final Integer number, final String city, final String province,
                                 final String zipCode, final String country) {
        return null;
    }
}
