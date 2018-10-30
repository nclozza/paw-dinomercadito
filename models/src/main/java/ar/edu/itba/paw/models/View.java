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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Post postVisited;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userWhoVisited;

    public View(final Post post, final User user){
        this.postVisited = post;
        this.userWhoVisited = user;
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


    public Post getPostVisited() {
        return postVisited;
    }

    public void setPostVisited(Post postVisited) {
        this.postVisited = postVisited;
    }

    public User getUserWhoVisited() {
        return userWhoVisited;
    }

    public void setUserWhoVisited(User userWhoVisited) {
        this.userWhoVisited = userWhoVisited;
    }
}
