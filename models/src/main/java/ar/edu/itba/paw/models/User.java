package ar.edu.itba.paw.models;

import java.time.LocalDate;

public class User {

    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Address address;
    private LocalDate birthdate;
    private Double funds;

    public User(String username, String password, String email, String phone, Address address, LocalDate birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
    }

    //TODO delete this
    public User(String username, String password, String email, String phone, String address, LocalDate birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getFunds() {
	return funds;
    }

    public void setFunds(Double funds) {
	this.funds = funds;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
