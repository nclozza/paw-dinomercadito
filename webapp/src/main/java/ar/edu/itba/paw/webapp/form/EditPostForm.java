package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditPostForm {

    @NotNull
    private Integer postId;

    @NotNull
    private Integer productId;

    // This is a String because the Double generates some problems with hibernate and the validators
    // TODO change the error to specify the regular expression to the user
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$")
    private String price;

    @Size(max = 128)
    private String description;

    @NotNull
    @Min(0)
    private Integer productQuantity;

    @NotNull
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(@NotNull final Integer productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @NotNull
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(@NotNull final Integer postId) {
        this.postId = postId;
    }

    @NotNull
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}