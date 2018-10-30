package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questions_questionId_seq")
    @SequenceGenerator(sequenceName = "questions_questionId_seq", name = "questions_questionId_seq", allocationSize = 1)
    @Column(name = "questionId")
    private Integer questionId;

    @Column(length = 128)
    private String question;

    @Column(length = 128)
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Post postAsked;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userWhoAsk;

    public Question(final Post post, final User userWhoAsk, final String question){
        this.postAsked = post;
        this.userWhoAsk = userWhoAsk;
        this.question = question;
    }

    public Question(){
        //Just for Hibernate
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Post getPostAsked() {
        return postAsked;
    }

    public void setPostAsked(Post postAsked) {
        this.postAsked = postAsked;
    }

    public User getUserWhoAsk() {
        return userWhoAsk;
    }

    public void setUserWhoAsk(User userWhoAsk) {
        this.userWhoAsk = userWhoAsk;
    }
}
