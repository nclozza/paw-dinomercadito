package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;

public class AddFundsForm {

    // This is a String because the Double generates some problems with hibernate and the validators
    // TODO change the error to specify the regular expression to the user
    @Pattern(regexp = "^[0-9]{1,8}(\\.[0-9]{1,2})?$")
    private String funds;

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }
}
