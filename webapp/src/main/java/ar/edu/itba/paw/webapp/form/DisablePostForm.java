package ar.edu.itba.paw.webapp.form;

public class DisablePostForm {

    private Integer postId ;

    private String filter;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
