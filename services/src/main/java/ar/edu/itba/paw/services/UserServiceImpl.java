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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PostService postService;


    public User createUser(final String username, final String password, final String email, final String phone, final String address, final LocalDate birthdate) {
        return userDAO.createUser(username, password, email, phone, address, birthdate);
    }

	public User findUserById(final Integer userId) {
		return userDAO.findUserById(userId);
	}

	public boolean deleteUser(final Integer userId) {
		return userDAO.deleteUser(userId);
	}


	public boolean updateUser(final String username, final String password, final String email, final String phone,
							  final String address, final LocalDate birthdate) {
		return userDAO.updateUser(username, password, email, phone, address, birthdate);
	}

	public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId) {
		boolean transactionSucceeded;
		Double price = postService.findPostById(postId).getPrice();
		Double buyerFunds = userDAO.findUserById(buyerId).getFunds();
		Double sellerFunds = userDAO.findUserById(sellerId).getFunds();

		if (buyerFunds - price < 0.00) {
			transactionSucceeded = false;
		} else {
			userDAO.findUserById(buyerId).setFunds(buyerFunds - price);
			userDAO.findUserById(sellerId).setFunds(sellerFunds + price);
			transactionSucceeded = true;
		}

		return transactionSucceeded;
	}

	public Post postProduct(final Product product, final Double price, final Integer userId, final String description) {
		return postService.createPost(product, price, userId, description);
	}
}
