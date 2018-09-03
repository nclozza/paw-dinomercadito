package ar.edu.itba.paw.models;

import java.util.Date;

public class User {

	private String name;
	private String password;
	private String email;
	private String phone;
	private Address address;
	private Date birthdate;
	private Double funds;

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

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getFunds() {
		return funds;
	}

	public void setFunds(Double funds) {
		this.funds = funds;
	}
}
