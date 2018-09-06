package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.Services.AddressService;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.itba.paw.interfaces.DAO.UserDAO;

import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.User;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PostService postService;

    public User createUserWithAddress(final String username, final String password, final String email, final String phone,
						   final LocalDate birthdate, final String street, final Integer number,final String city,
						   final String province, final String zipCode, final String country) {

        User user = userDAO.createUser(username, password, email, phone, birthdate);

        addressService.createAddress(user.getUserId(), street, number, city, province, zipCode,country);

        return user;
    }

    public User createUser(final String username, final String password, final String email, final String phone,
                           final LocalDate birthdate) {

        return userDAO.createUser(username, password, email, phone, birthdate);
    }

	public User findUserByUserId(final Integer userId) {
		return userDAO.findUserByUserId(userId);
	}

	// TODO See what to do with this method's return value
	public boolean deleteUser(final Integer userId) {
    	boolean deletionSucceeded = true;

    	List<Post> postsList = postService.findPostsByUserId(userId);

    	if (postsList != null && !postsList.isEmpty()) {
			for (Post post : postsList) {
				postService.deletePost(post.getPostId());
			}
		} else {
    		return !deletionSucceeded;
		}

		if (userDAO.deleteUser(userId) == deletionSucceeded)
			return deletionSucceeded;
		else
			return !deletionSucceeded;
	}

	public boolean updateUserFunds(Integer userId, Double funds) {
    	boolean fundsUpdateSucceeded = true;

    	if (userId < 0 || funds < 0.0)
    		return !fundsUpdateSucceeded;

		return userDAO.updateUserFunds(userId, funds);
	}

	public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId) {
		boolean transactionSucceeded = true;
		Double price = postService.findPostById(postId).getPrice();
		Double buyerFunds = userDAO.findUserByUserId(buyerId).getFunds();
		Double sellerFunds = userDAO.findUserByUserId(sellerId).getFunds();

		if (buyerId < 0 || sellerId < 0 || postId < 0)
			return !transactionSucceeded;

		if (buyerFunds - price < 0.00) {
			return !transactionSucceeded;
		} else {
			userDAO.updateUserFunds(buyerId, buyerFunds - price);
			userDAO.updateUserFunds(sellerId, sellerFunds + price);
		}

		return transactionSucceeded;
	}

	public boolean postProduct(final Integer productId, final Double price, final Integer userId, final String description) {
    	boolean postProductSucceeded = true;
    	if (productId < 0 || price < 0.0 || userId < 0 || description == null)
    		throw new IllegalArgumentException();

		if (postService.createPost(productId, price, userId, description) == null)
			return !postProductSucceeded;
		else
			return postProductSucceeded;
	}
}
