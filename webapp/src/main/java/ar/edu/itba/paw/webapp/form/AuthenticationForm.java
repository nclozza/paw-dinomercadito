package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotNull;

public class AuthenticationForm {

    @NotNull
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }
}
