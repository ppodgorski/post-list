package com.ppodgorski.postlist.view.postdetails;

import com.ppodgorski.postlist.model.Comment;
import com.ppodgorski.postlist.model.Post;
import com.ppodgorski.postlist.model.User;
import com.ppodgorski.postlist.view.base.BasePresenter;
import com.ppodgorski.postlist.view.base.BaseView;

import java.util.List;

public interface PostDetailsContract {

    interface View extends BaseView<Presenter> {

        void setupPostViews(Post post);
        void setupUserViews(User user);
        void setupCommentsViews(List<Comment> comments);
        void hideDetailsContainer();
        void showDetailsContainer();
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showError();
    }

    interface Presenter extends BasePresenter<View> {

        void getData();
    }

}
