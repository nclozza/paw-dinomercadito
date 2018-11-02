package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransactionForm {

    private Integer postId;

    @NotNull
    @Min(0)
    private Integer productQuantity;

    private String filter;
    private Boolean profile;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(final Integer postId) {
        this.postId = postId;
    }

    @NotNull
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(@NotNull Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Boolean getProfile() {
        return profile;
    }

    public void setProfile(Boolean profile) {
        this.profile = profile;
    }
}
