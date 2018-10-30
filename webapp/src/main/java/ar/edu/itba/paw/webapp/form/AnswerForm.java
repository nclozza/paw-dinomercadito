package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerForm {

    @Size(min = 1, max = 128)
    private String answer;

    @NotNull
    private Integer questionId;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @NotNull
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(@NotNull Integer questionId) {
        this.questionId = questionId;
    }
}
