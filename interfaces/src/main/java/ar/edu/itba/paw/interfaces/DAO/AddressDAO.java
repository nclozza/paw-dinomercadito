package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Address;

import java.util.List;

public interface AddressDAO {

    Address createAddress(final Integer userId, final String street, final Integer number, final String city,
                          final String province, final String zipCode, final String country);

    boolean deleteAddressByAddressId(final Integer addressId);

    boolean deleteAddressByUserId(final Integer userId);

    Address findAddressByAddressId(final Integer addressId);

    Address updateAddress(final Integer addressId, final String street,
                          final Integer number, final String city, final String province, final String zipCode,
                          final String country);

    List<Address> findAddressesByUserId(final Integer userId);
}
