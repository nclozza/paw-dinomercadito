package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.View;

import java.util.List;
import java.util.Optional;

public interface ViewService {

    View createView(Integer postId, Integer userId);

    Optional<View> findViewByViewId(Integer viewId);

    List<View> findViewsByUserId(Integer userId);

    List<View> findViewsByPostId(Integer postId);

    boolean checkAddVisit(Integer postId, Integer userId);

    List<View> findLastViewsByUserId(Integer userId);
}
