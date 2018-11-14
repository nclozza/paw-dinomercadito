package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_addressId_seq")
    @SequenceGenerator(sequenceName = "addresses_addressId_seq", name = "addresses_addressId_seq", allocationSize = 1)
    @Column(name = "addressId")
    private Integer addressId;

    @Column(nullable = false, length = 32)
    private String street;

    @Column(nullable = false)
    private Integer number;

    @Column(length = 32)
    private String city;

    @Column(length = 32)
    private String province;

    @Column(length = 16, nullable = false)
    private String zipCode;

    @Column(length = 32)
    private String country;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userAddress;

    public Address(final Integer addressId, final Integer userId, final String street, final Integer number,
                   final String city, final String province, final String zipCode, final String country) {
        this.addressId = addressId;
        this.userAddress.setUserId(userId);
        this.street = street;
        this.number = number;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address(final User user, final String street, final Integer number,
                   final String city, final String province, final String zipCode, final String country) {
        this.addressId = addressId;
        this.userAddress = user;
        this.street = street;
        this.number = number;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address(){
        //Just for Hibernate
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(final Integer addressId) {
        this.addressId = addressId;
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

    public User getUser() {
        return userAddress;
    }

    public void setUserAddress(User userAddress) {
        this.userAddress = userAddress;
    }
}
