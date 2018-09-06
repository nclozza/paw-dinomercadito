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

    public User createUserWithAddress(final String username, final String password, final String email,
									  final String phone, final LocalDate birthdate, final String street,
									  final Integer number,final String city, final String province,
									  final String zipCode, final String country) {

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

    	List<Post> postsList = postService.findPostByUserId(userId);

    	if (postsList != null && !postsList.isEmpty()) {
			for (Post post : postsList) {
				postService.deletePost(post.getPostId());
			}
		} else {
    		return !deletionSucceeded;
		}

		List<Address> addressesList = addressService.findAddressByUserId(userId);

		if (addressesList != null && !addressesList.isEmpty()) {
			for (Address address : addressesList) {
				addressService.deleteAddress(address.getAddressId());
			}
		} else {
			return !deletionSucceeded;
		}

		if (userDAO.deleteUser(userId) == deletionSucceeded)
			return deletionSucceeded;
		else
			return !deletionSucceeded;
	}

	public User updateUser(Integer userId, String username, String password, String email, String phone, LocalDate birthdate) {
		return userDAO.updateUser(userId, username, password, email, phone, birthdate);
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
