package com.ppodgorski.postlist.view.postlist;

import android.support.annotation.Nullable;

import com.ppodgorski.postlist.di.scope.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class PostListPresenter implements PostListContract.Presenter {

    @Nullable
    private PostListContract.View mPostListView;

    @Inject
    public PostListPresenter() {

    }

    @Override
    public void takeView(PostListContract.View view) {
        mPostListView = view;
    }

    @Override
    public void dropView() {
        mPostListView = null;
    }
}
