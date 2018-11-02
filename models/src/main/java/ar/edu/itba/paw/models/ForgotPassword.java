package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "forgotPasswords")
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forgotPasswords_forgotPasswordId_seq")
    @SequenceGenerator(sequenceName = "forgotPasswords_forgotPasswordId_seq", name = "forgotPasswords_forgotPasswordId_seq", allocationSize = 1)
    @Column(name = "forgotPasswordId")
    private Integer forgotPasswordId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userForgot;

    @Column(length = 10)
    private String requestDate;

    @Column(length = 32)
    private String code;

    public ForgotPassword(User userForgot, String requestDate, String code) {
        this.userForgot = userForgot;
        this.requestDate = requestDate;
        this.code = code;
    }

    public ForgotPassword(){
        //Just for Hibernate
    }

    public Integer getForgotPasswordId() {
        return forgotPasswordId;
    }

    public void setForgotPasswordId(Integer forgotPasswordId) {
        this.forgotPasswordId = forgotPasswordId;
    }

    public User getUserForgot() {
        return userForgot;
    }

    public void setUserForgot(User userForgot) {
        this.userForgot = userForgot;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
