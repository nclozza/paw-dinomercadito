package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.QuestionDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Question;
import ar.edu.itba.paw.models.User;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class QuestionDAOHibernate implements QuestionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDAOHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Question createQuestion(final Integer postId, final Integer userWhoAskId, final String question) {
        Post post = em.find(Post.class, postId);
        User userWhoAsk = em.find(User.class, userWhoAskId);
        Question questions = new Question(post, userWhoAsk, question);
        em.persist(questions);
        LOGGER.info("Question inserted with questionId = {}", questions.getQuestionId().intValue());
        return questions;
    }

    @Override
    public List<Question> findQuestionsByPostId(Integer postId){
        Post post = em.find(Post.class, postId);
        return post.getQuestionList();
    }

    @Override
    public List<Question> findQuestionsByUserWhoAskId(Integer userWhoAskId){
        User userWhoAsk = em.find(User.class, userWhoAskId);
        return userWhoAsk.getQuestionList();
    }

    @Override
    public List<Question> findPendingQuestionsByPostId(Integer postId){
        final TypedQuery<Question> query = em.createQuery("FROM Question q WHERE q.postId = :postId and q.answer is null", Question.class);
        query.setParameter("postId", postId);
        return query.getResultList();
    }
}
