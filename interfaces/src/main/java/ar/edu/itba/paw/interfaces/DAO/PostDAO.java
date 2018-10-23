package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO {

    /**
     * Creates an online post POJO and stores it in the database. The post will consist of a product, its price, the
     * product's seller username, a product description and the quantity in stock for offer.
     * @param productId The product's unique ID as an Integer.
     * @param price The price set for this product by the seller as a Double. It can be up to 8 figures and 2
     *              decimals.
     * @param userId The seller's unique user ID as an Integer.
     * @param description The post's description as a String up to a maximum of 128 characters.
     * @param visits The post's visits as an Integer.
     * @return A new POJO of a post. This method will never return null.
     */
    Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                    final Integer visits);

    /**
     * Deletes a post from the database when given the post's ID.
     * @param postId The post's ID as an Integer to identify and delete said post.
     * @return True if the post was successfully deleted from the database. False otherwise.
     */
    boolean deletePost(final Integer postId);

    /**
     * Updates a post's information in the database, given its ID. The post's values that can be edited are: its
     * product ID, its price, its description and its quantity for that product.
     * @param postId The post's ID as an Integer to identify and edit said post.
     * @param productId The possibly new product ID as an Integer to associate this post with.
     * @param price The possibly new post's price as a Double.
     * @param description The possibly new post's description as a String.
     * @param visits The possibly new visits for this post.
     * @return An Optional object that contains either the new post POJO if the post's information was successfully
     *      updated in the database, or an empty Optional otherwise.
     */
    Optional<Post> updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                    final Integer visits);

    /**
     * Finds a post through its unique ID in the database.
     * @param postId The post's ID as an Integer to identify and obtain said post.
     * @return An Optional object that contains either the specified post POJO if the post was successfully found and
     *      retrieved from the database, or an empty Optional otherwise.
     */
    Optional<Post> findPostByPostId(final Integer postId);

    /**
     * Finds all posts owned by a certain user ID in the database.
     * @param userId The post's seller user ID as an Integer to identify and obtain said post.
     * @return A List of posts posted by the specified user if said posts were found and successfully retrieved from
     *      the database. The return value will be an empty list if either there are no posts posted by that user or
     *      there was an error with the retrieval of said posts from the database.
     */
    List<Post> findPostsByUserId(final Integer userId);

    /**
     * Finds all posts associated to a certain product, through that product's ID, in the database.
     * @param productId The post's product ID as an Integer to identify and obtain said post.
     * @return A List of posts associated with the specified product if said posts were found and successfully retrieved
     *      from the database. The return value will be an empty List if either there are no posts offering that product
     *      or there was an error with the retrieval of said posts from the database.
     */
    List<Post> findPostsByProductId(final Integer productId);

    Optional<Post> addVisit(final Integer postId);
}
