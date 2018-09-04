package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.itba.paw.interfaces.UserDAO;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PostService postService;

    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public User createUser(final String username, final String password, final String email, final String phone, final String address, final LocalDate birthdate) {
        return userDAO.createUser(username, password, email, phone, address, birthdate);
    }

	public User getUser(String username) {
		return null;
	}

	public boolean deleteUser(String username) {
		return userDAO.deleteUser(username);
	}


	public boolean updateUser(final String username, final String password, final String email, final String phone,
							  final String address, final LocalDate birthdate) {
		return userDAO.updateUser(username, password, email, phone, address, birthdate);
	}

	public boolean buy(final String buyerUsername, final String sellerUsername, final Integer postId) {
		boolean transactionSucceded;
		Double price = postService.getPost(postId).getPrice();
		Double buyerFunds = userDAO.getUser(buyerUsername).getFunds();
		Double sellerFunds = userDAO.getUser(sellerUsername).getFunds();


		if (buyerFunds - price < 0.00) {
			transactionSucceded = false;
		} else {
			userDAO.getUser(buyerUsername).setFunds(buyerFunds - price);
			userDAO.getUser(sellerUsername).setFunds(sellerFunds + price);
			transactionSucceded = true;
		}

		return transactionSucceded;
	}

	public Post postProduct(final Product product, final Double price, final String username, final String description) {
		return postService.createPost(product, price, username, description);
	}
}
