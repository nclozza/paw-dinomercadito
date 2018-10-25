package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SearchForm {

    @Size(max = 100)
    @Pattern(regexp = "[a-zA-Z0-9 ]*|^$")
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(final String search) {
        this.search = search;
    }
}
