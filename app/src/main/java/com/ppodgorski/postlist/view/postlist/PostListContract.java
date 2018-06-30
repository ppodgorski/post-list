package com.ppodgorski.postlist.view.postlist;

import com.ppodgorski.postlist.model.Post;
import com.ppodgorski.postlist.view.base.BasePresenter;
import com.ppodgorski.postlist.view.base.BaseView;

import java.util.List;

public interface PostListContract {

    interface View extends BaseView<Presenter> {

        void showPosts(List<Post>posts);
        void hidePosts();
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showError();
    }

    interface Presenter extends BasePresenter<View> {

        void getPosts();
    }

}
