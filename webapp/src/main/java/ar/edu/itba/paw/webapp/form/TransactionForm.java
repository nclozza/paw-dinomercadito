package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotNull;

public class TransactionForm {

    private Integer postId;

    @NotNull
    private Integer productQuantity;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(final Integer postId) {
        this.postId = postId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(final Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public boolean checkQuantity(final Integer availableQuantity) {
        return productQuantity <= availableQuantity;
    }
}
