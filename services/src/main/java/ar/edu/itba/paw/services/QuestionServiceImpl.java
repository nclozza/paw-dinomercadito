package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.QuestionDAO;
import ar.edu.itba.paw.interfaces.Services.QuestionService;
import ar.edu.itba.paw.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public Question createQuestion(final Integer postId, final Integer userWhoAskId, final String question) {
        return questionDAO.createQuestion(postId, userWhoAskId, question);
    }

    @Override
    public List<Question> findQuestionsByPostId(Integer postId){
        return questionDAO.findQuestionsByPostId(postId);
    }

    @Override
    public List<Question> findQuestionsByUserWhoAskId(Integer userWhoAskId){
        return questionDAO.findQuestionsByUserWhoAskId(userWhoAskId);
    }

    @Override
    public List<Question> findPendingQuestionsByPostId(Integer postId){
        return questionDAO.findPendingQuestionsByPostId(postId);
    }
}
