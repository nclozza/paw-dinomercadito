package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.*;

public class PostForm {

    @NotNull
    private Integer productId;

    // This is a String because the Double generates some problems with hibernate and the validators
    // TODO change the error to specify the regular expression to the user
    @Pattern(regexp = "^[0-9]{1,8}+(\\.[0-9]{1,2})?$")
    private String price;

    @Size(max = 128)
    private String description;

    @NotNull
    @Min(1)
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
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(@NotNull final Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
