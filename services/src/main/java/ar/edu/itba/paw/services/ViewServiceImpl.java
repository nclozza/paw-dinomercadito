package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.ViewDAO;
import ar.edu.itba.paw.interfaces.Services.ViewService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ViewServiceImpl implements ViewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewServiceImpl.class);

    @Autowired
    private ViewDAO viewDAO;

    @Override
    public View createView(final Integer postId, final Integer userId) {
        return viewDAO.createView(postId, userId);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<View> findViewByViewId(final Integer viewId) {
        return viewDAO.findViewByViewId(viewId);
    }

    @Transactional (readOnly = true)
    @Override
    public List<View> findViewsByUserId(final Integer userId) {
        return viewDAO.findViewsByUserId(userId);
    }

    @Transactional (readOnly = true)
    @Override
    public List<View> findViewsByPostId(final Integer postId) {
        return viewDAO.findViewsByPostId(postId);
    }

    @Override
    public boolean checkAddVisit(Integer postId, Integer userId){
        List<View> viewsList = viewDAO.findViewsByPostId(postId);

        for (View v: viewsList){
            if(v.getUserId() == userId)
                return false;
        }

        return true;
    }
}
