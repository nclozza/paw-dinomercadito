package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_userId_seq")
    @SequenceGenerator(sequenceName = "users_userId_seq", name = "users_userId_seq", allocationSize = 1)
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

    @Column()
    private Double funds;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
    private List<Post> postList;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
    private List<Address> addressesList;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "buyerUser")
    private List<Transaction> transactionsList;

    public User(final String username, final String password, final String email,
                final String phone, final String birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.funds = 0.0;
    }

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

    public User(){
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

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Address> getAddressesList() {
        return addressesList;
    }

    public void setAddressesList(List<Address> addressesList) {
        this.addressesList = addressesList;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }
}
