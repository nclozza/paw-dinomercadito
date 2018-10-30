package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "views")
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "views_viewId_seq")
    @SequenceGenerator(sequenceName = "views_viewId_seq", name = "views_viewId_seq", allocationSize = 1)
    @Column(name = "viewId")
    private Integer viewId;

    @Column(nullable = false)
    private Integer postId;

    @Column(nullable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Post postInView;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    public View(final Post post, final User user){
        this.postInView = post;
        this.postId = post.getPostId();
        this.user = user;
        this.userId = user.getUserId();
    }

    public View(){
        //Just for Hibernate
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Post getPostInView() {
        return postInView;
    }

    public void setPostInView(Post postInView) {
        this.postInView = postInView;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
