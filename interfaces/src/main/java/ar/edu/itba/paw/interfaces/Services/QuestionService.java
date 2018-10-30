package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Question createQuestion(final Integer postId, final Integer userWhoAskId, final String question);

    List<Question> findQuestionsByPostId(Integer postId);

    List<Question> findQuestionsByUserWhoAskId(Integer userWhoAskId);

    List<Question> findPendingQuestionsByUserId(Integer userId);

    Optional<Question> findQuestionsByQuestionId(Integer questionId);

    Optional<Question> addAnswer(Integer questionId, String answer);
}
