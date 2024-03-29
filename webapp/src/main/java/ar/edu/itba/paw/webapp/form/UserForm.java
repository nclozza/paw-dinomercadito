package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    @Size(min = 6, max = 32)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String username;

    @Size(min = 6, max = 32)
    private String password;

    @NotNull
    private String repeatPassword;

    @Size(max = 32)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Size(min = 8, max = 16)
    @Pattern(regexp = "[0-9]*")
    private String phone;

    @Size(min = 10, max = 10)
    private String birthdate;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(final String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public boolean checkPassword() {
        return this.password != null && this.repeatPassword != null && this.password.equals(this.repeatPassword);
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
}
