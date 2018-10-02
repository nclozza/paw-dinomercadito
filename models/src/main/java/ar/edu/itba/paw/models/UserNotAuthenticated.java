package ar.edu.itba.paw.models;

public class UserNotAuthenticated {

    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String birthdate;
    private String signUpDate;
    private Integer code;

    public UserNotAuthenticated(final Integer userId, final String username, final String password, final String email,
                                final String phone, final String birthdate, final String signUpDate,
                                final Integer code) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.signUpDate = signUpDate;
        this.code = code;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(final String signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }
}
