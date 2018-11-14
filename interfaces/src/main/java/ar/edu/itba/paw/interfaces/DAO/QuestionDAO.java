package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDAO {
    Question createQuestion(final Integer postId, final Integer userWhoAskId, final String question);

    List<Question> findQuestionsByPostId(Integer postId);

    List<Question> findQuestionsByUserWhoAskId(Integer userWhoAskId);

    List<Question> findPendingQuestionsByUserId(Integer userId);

    Optional<Question> findQuestionByQuestionId(Integer questionId);

    Optional<Question> addAnswer(Integer questionId, String answer);
}
