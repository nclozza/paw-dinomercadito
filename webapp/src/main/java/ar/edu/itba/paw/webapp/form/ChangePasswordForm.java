package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Size;

public class ChangePasswordForm {

    @Size(min = 6, max = 32)
    private String newPassword;

    private String repeatPassword;

    private String code;

    public boolean checkPassword() {
        return this.newPassword != null && this.newPassword.equals(this.repeatPassword);
    }

    public String getPassword() {
        return newPassword;
    }

    public void setPassword(String password) {
        this.newPassword = password;
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
