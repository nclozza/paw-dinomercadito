package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Question;

import java.util.List;

public interface QuestionDAO {
    Question createQuestion(final Integer postId, final Integer userWhoAskId, final String question);

    List<Question> findQuestionsByPostId(Integer postId);

    List<Question> findQuestionsByUserWhoAskId(Integer userWhoAskId);

    List<Question> findPendingQuestionsByPostId(Integer postId);


}
