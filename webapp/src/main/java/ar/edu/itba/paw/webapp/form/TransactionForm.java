package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransactionForm {

    private Integer postId;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(final Integer postId) {
        this.postId = postId;
    }

}
