package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Address;

import java.util.List;

public interface AddressDAO {

    public Address createAddress(final Integer userId, final String street, final Integer number, final String city,
                                 final String province, final String zipCode, final String country);

    public boolean deleteAddressByAddressId(final Integer addressId);

    public boolean deleteAddressByUserId(final Integer userId);

    public Address findAddressByAddressId(final Integer addressId);

    public Address updateAddress(final Integer addressId, final Integer userId, final String street,
                                 final Integer number, final String city, final String province, final String zipCode,
                                 final String country);

    public List<Address> findAddressesByUserId(Integer userId);
}
