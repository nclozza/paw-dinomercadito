package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PostForm {

    @NotNull
    private Integer productId;

    // This is a String because the Double generates some problems with hibernate and the validators
    // TODO change the error to specify the regular expression to the user
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$")
    private String price;

    @Size(max = 128)
    private String description;

    @NotNull
    private Integer productQuantity;

    @NotNull
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(@NotNull Integer productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(@NotNull Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
