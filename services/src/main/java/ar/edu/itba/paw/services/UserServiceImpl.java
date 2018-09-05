package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.itba.paw.interfaces.UserDAO;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PostService postService;


    public User createUser(final String username, final String password, final String email, final String phone,
						   final Integer addressId, final LocalDate birthdate) {
        return userDAO.createUser(username, password, email, phone, addressId, birthdate);
    }

	public User findUserById(final Integer userId) {
		return userDAO.findUserById(userId);
	}

	// TODO See what to do with this method's return value
	public boolean deleteUser(final Integer userId) {
    	List<Post> postsList = postService.findPostsByUserId(userId);

    	if (!postsList.isEmpty()) {
			for (Post post : postsList) {
				postService.deletePost(post.getPostId());
			}
		}

		userDAO.deleteUser(userId);

    	return true;
	}

	@Override
	public boolean updateUserByFunds(Integer userId, Double funds) {
		return userDAO.updateUserByFunds(userId, funds);
	}

	public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId) {
		boolean transactionSucceeded;
		Double price = postService.findPostById(postId).getPrice();
		Double buyerFunds = userDAO.findUserById(buyerId).getFunds();
		Double sellerFunds = userDAO.findUserById(sellerId).getFunds();

		if (buyerFunds - price < 0.00) {
			transactionSucceeded = false;
		} else {
			userDAO.updateUserByFunds(buyerId, buyerFunds - price);
			userDAO.updateUserByFunds(sellerId, sellerFunds + price);
			transactionSucceeded = true;
		}

		return transactionSucceeded;
	}

	public Post postProduct(final Integer productId, final Double price, final Integer userId, final String description) {
		return postService.createPost(productId, price, userId, description);
	}
}
