package ar.edu.itba.paw.models;

public class Address {

    private Integer addressId;
    private Integer userId;
    private String street;
    private Integer number;
    private String city;
    private String province;
    private String zipCode;
    private String country;

    public Address(final Integer addressId, final Integer userId, final String street, final Integer number,
                   final String city, final String province, final String zipCode, final String country) {
        this.addressId = addressId;
        this.userId = userId;
        this.street = street;
        this.number = number;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(final Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(final String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }
}
