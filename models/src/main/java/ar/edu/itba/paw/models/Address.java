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

    public Address(Integer addressId, Integer userId, String street, Integer number, String city, String province,
                   String zipCode, String country) {
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

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
