package ar.edu.itba.paw.models;

import java.util.Date;

public class User {

    private String username;
    private String password;
    private String email;
    private String phone;
    private Address address;
    private Date birthdate;

    public User(String username, String password, String email, String phone, Address address, Date birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
    }

    public User(String username, String password, String email, String phone, String address, Date birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
