package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Address;

import java.util.List;

public interface AddressService {

    Address createAddress(final Integer userId, final String street, final Integer number, final String city,
                          final String province, final String zipCode, final String country);

    Address findAddressByAddressId(final Integer addressId);

    boolean deleteAddress(final Integer addressId);

    Address updateAddress(final String street, final Integer number, final String city, final String province,
                          final String zipCode, final String country);

    List<Address> findAddressByUserId(final Integer userId);
}
