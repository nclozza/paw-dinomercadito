package ar.edu.itba.paw.models;

public class User {

    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String birthdate;
    private Double funds;

    public User(final Integer userId, final String username, final String password, final String email,
                final String phone, final String birthdate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.funds = 0.0;
    }

    public User(final Integer userId, final String username, final String password, final String email,
                final String phone, final String birthdate, final Double funds) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.funds = funds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(final String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Double getFunds() {
        return funds;
    }

    public void setFunds(final Double funds) {
        this.funds = funds;
    }
}
