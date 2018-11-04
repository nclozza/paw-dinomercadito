package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Size;

public class ChangePasswordForm {

    @Size(min = 6, max = 32)
    private String password;

    private String repeatPassword;

    private String code;

    public boolean checkPassword() {
        return this.password != null && this.repeatPassword != null && this.password.equals(this.repeatPassword);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
