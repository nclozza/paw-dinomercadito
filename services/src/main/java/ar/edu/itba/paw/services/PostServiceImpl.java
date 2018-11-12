package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.interfaces.Services.ViewService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class PostServiceImpl implements PostService {


    @Autowired
    private PostDAO postDAO;

    @Autowired
    private ViewService viewService;

    @Override
    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                           final Integer productQuantity, final Integer visits) {
        return postDAO.createPost(productId, price, userId, description, productQuantity, visits);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<Post> findPostByPostId(final Integer postId) {
        return postDAO.findPostByPostId(postId);
    }

    @Transactional (readOnly = true)
    @Override
    public List<Post> findPostsByUserId(Integer userId) {
        List<Post> postsList = postDAO.findPostsByUserId(userId);

        return postsList;
    }

    @Transactional (readOnly = true)
    @Override
    public List<Post> findPostsByProductId(Integer productId) {
        List<Post> postsList = postDAO.findPostsByProductId(productId);

        return postsList;
    }

    @Override
    public Optional<Post> addVisit(Integer postId, Integer userId, Integer userIdLogged) {

        if(userId != userIdLogged && viewService.checkAddVisit(postId, userIdLogged)){
            Optional<Post> post = postDAO.addVisit(postId);
            viewService.createView(postId, userIdLogged);
            return post;
        }

        return null;
    }

    @Override
    public Optional<Post> updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                                     final Integer productQuantity, final Integer visits) {
        return postDAO.updatePost(postId, productId, price, description, productQuantity, visits);
    }

    @Override
    public boolean deletePost(final Integer postId) {
        return postDAO.deletePost(postId);
    }

    @Override
    public List<Post> filterByAvailablePosts(final List<Post> postList) {
        List<Post> resultList = new LinkedList<Post>();

        for(Post p : postList){
            if (p.getProductQuantity() > 0)
                resultList.add(p);
        }

        return resultList;
    }

    @Override
    public List<Post> findMostVisitedPosts(){
        List<Post> postList = postDAO.findMostVisitedPosts();
        List<Integer> productIdList = new LinkedList<Integer>();
        List<Post> resultList = new LinkedList<Post>();

        for(Post p: postList){
            if (!productIdList.contains(p.getProductPosted().getProductId()) && p.getProductQuantity() > 0 && p.getVisits() > 0) {
                productIdList.add(p.getProductPosted().getProductId());
                resultList.add(p);
                if(resultList.size() == Post.MAX_TOP_VISITED)
                    return resultList;
            }
        }

        return resultList;
    }

    @Override
    public Optional<Post> updatePostProductQuantity(Integer postId, Integer productQuantity) {
        return postDAO.updatePostProductQuantity(postId, productQuantity);
    }
}
