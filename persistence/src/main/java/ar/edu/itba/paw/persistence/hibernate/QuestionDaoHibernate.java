package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.DAO.QuestionDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Question;
import ar.edu.itba.paw.models.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDaoHibernate implements QuestionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDaoHibernate.class);

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
    public Optional<Question> findQuestionByQuestionId(Integer questionId){
        return Optional.ofNullable(em.find(Question.class, questionId));
    }

    @Transactional
    @Override
    public List<Question> findQuestionsByPostId(Integer postId){
        Post post = em.find(Post.class, postId);
        Hibernate.initialize(post.getQuestionList());
        return  post.getQuestionList();
    }

    @Transactional
    @Override
    public List<Question> findQuestionsByUserWhoAskId(Integer userWhoAskId){
        User userWhoAsk = em.find(User.class, userWhoAskId);
        Hibernate.initialize(userWhoAsk.getQuestionList());
        return userWhoAsk.getQuestionList();
    }

    @Override
    public List<Question> findPendingQuestionsByUserId(Integer userId){
        final TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q " +
                "INNER JOIN Post p " +
                "ON p.postId = q.postAsked.postId " +
                "WHERE p.userSeller.userId = :userId " +
                "AND q.answer IS NULL", Question.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Question> addAnswer(Integer questionId, String answer){
        Question question = em.find(Question.class, questionId);

        if (question != null) {
            question.setAnswer(answer);
            em.merge(question);
            LOGGER.info("Answer added with questionId = {}", questionId);
        } else {
            LOGGER.info("Question not found with questionId = {}", questionId);
        }

        return Optional.ofNullable(question);
    }
}
