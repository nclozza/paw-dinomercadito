package ar.edu.itba.paw.models;

import java.time.LocalDate;
import java.util.HashMap;

public class User {

    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer addressId;
    private LocalDate birthdate;
    private Double funds;

    public User(String username, String password, String email, String phone, Integer addressId, LocalDate birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.addressId = addressId;
        this.birthdate = birthdate;
        funds = 0.0;
    }

    public User(String username, String password, String email, String phone, Integer addressId, LocalDate birthdate,
                Double funds) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.addressId = addressId;
        this.birthdate = birthdate;
        this.funds = funds;
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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
