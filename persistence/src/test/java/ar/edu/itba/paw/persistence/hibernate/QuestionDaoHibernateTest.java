package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testQuestions.sql")
public class QuestionDaoHibernateTest {

    private static final int QUESTION_ID = 8888;
    private static final int POST_ID = 99999;
    private static final int USER_WHO_ASKED_ID = 5555;
    private static final int USER_ID = 99997;
    private static final String QUESTION = "?";
    private static final String ANSWER = "answer";


    @Autowired
    private QuestionDaoHibernate questionDaoHibernate;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM questions");
        em.flush();
    }

    @Test
    public void testCreateQuestion() {
        final Question question = questionDaoHibernate.createQuestion(POST_ID, USER_WHO_ASKED_ID, QUESTION);

        assertNotNull(question);
        assertEquals(POST_ID, question.getPostAsked().getPostId().intValue());
        assertEquals(USER_WHO_ASKED_ID, question.getUserWhoAsk().getUserId().intValue());
        assertEquals(QUESTION, question.getQuestion());
        assertTrue(em.contains(question));
    }

    @Test
    public void testFindQuestionByQuestionId() {
        final Optional<Question> question = questionDaoHibernate.findQuestionByQuestionId(QUESTION_ID);

        assertTrue(question.isPresent());
        assertEquals(QUESTION_ID, question.get().getQuestionId().intValue());
    }

    @Test
    public void testFindQuestionsByPostId() {
        final List<Question> questionList = questionDaoHibernate.findQuestionsByPostId(POST_ID);

        assertFalse(questionList.isEmpty());

        for (Question q : questionList)
            assertEquals(POST_ID, q.getPostAsked().getPostId().intValue());
    }

    @Test
    public void testFindQuestionsByUserWhoAskId() {
        final List<Question> questionList = questionDaoHibernate.findQuestionsByUserWhoAskId(USER_WHO_ASKED_ID);

        assertFalse(questionList.isEmpty());

        for (Question q : questionList)
            assertEquals(USER_WHO_ASKED_ID, q.getUserWhoAsk().getUserId().intValue());
    }

    @Test
    public void testFindPendingQuestionsByUserId() {
        final List<Question> questionList = questionDaoHibernate.findPendingQuestionsByUserId(USER_ID);

        assertFalse(questionList.isEmpty());

        for (Question q : questionList) {
            assertEquals(USER_ID, q.getPostAsked().getUserSeller().getUserId().intValue());
            assertNull(q.getAnswer());
        }
    }

    @Test
    public void testAddAnswer() {
        final Optional<Question> question = questionDaoHibernate.addAnswer(QUESTION_ID, ANSWER);

        assertTrue(question.isPresent());
        assertEquals(QUESTION_ID, question.get().getQuestionId().intValue());
        assertEquals(ANSWER, question.get().getAnswer());
    }
}
