package ar.edu.itba.paw.webapp.form;

public class TransactionForm {

    private Integer postId;

    private Integer productQuantity;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public boolean checkQuantity(final Integer availableQuantity) {
        return productQuantity <= availableQuantity;
    }
}
