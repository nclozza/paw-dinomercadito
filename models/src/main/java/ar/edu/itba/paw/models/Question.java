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

    @Column(nullable = false)
    private Integer postId;

    @Column(nullable = false)
    private Integer userWhoAskId;

    @Column(length = 128)
    private String question;

    @Column(length = 128)
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userWhoAsk;

    public Question(final Post post, final User userWhoAsk, final String question){
        this.post = post;
        this.postId = post.getPostId();
        this.userWhoAsk = userWhoAsk;
        this.userWhoAskId = userWhoAsk.getUserId();
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserWhoAskId() {
        return userWhoAskId;
    }

    public void setUserWhoAskId(Integer userWhoAskId) {
        this.userWhoAskId = userWhoAskId;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUserWhoAsk() {
        return userWhoAsk;
    }

    public void setUserWhoAsk(User userWhoAsk) {
        this.userWhoAsk = userWhoAsk;
    }
}
