package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "usersNotAuthenticated")
public class UserNotAuthenticated {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersNotAuthenticated_userId_seq")
    @SequenceGenerator(sequenceName = "usersNotAuthenticated_userId_seq", name = "usersNotAuthenticated_userId_seq", allocationSize = 1)
    @Column(name = "userId")
    private Integer userId;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 32)
    private String email;

    @Column(length = 16)
    private String phone;

    @Column(length = 10)
    private String birthdate;

    @Column(length = 10)
    private String signUpDate;

    @Column
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

    public UserNotAuthenticated(final String username, final String password, final String email,
                                final String phone, final String birthdate, final String signUpDate,
                                final Integer code) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.signUpDate = signUpDate;
        this.code = code;
    }

    public UserNotAuthenticated(){
        //Just for Hibernate
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
