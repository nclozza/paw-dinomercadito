package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuestionForm {

    @NotNull
    private Integer postId;

    @Size(min = 1, max = 128)
    private String question;

    private String filter;
    private Boolean profile;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @NotNull
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(@NotNull Integer postId) {
        this.postId = postId;
    }

    public Boolean getProfile() {
        return profile;
    }

    public void setProfile(Boolean profile) {
        this.profile = profile;
    }
}
