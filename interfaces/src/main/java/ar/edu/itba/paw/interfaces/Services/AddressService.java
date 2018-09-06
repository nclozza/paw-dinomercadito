package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Address;

import java.util.List;

public interface AddressService {

    public Address createAddress(final Integer userId, final String street, final Integer number, final String city,
                                 final String province, final String zipCode, final String country);

    public Address findAddressByAddressId(final Integer addressId);

    public boolean deleteAddress(final Integer addressId);

    public Address updateAddress(final String street, final Integer number, final String city, final String province,
                                 final String zipCode, final String country);

    public List<Address> findAddressByUserId(Integer userId);
}
