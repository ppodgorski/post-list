package com.ppodgorski.postlist.view.postdetails;

import com.ppodgorski.postlist.network.ApiService;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class PostDetailsPresenter implements PostDetailsContract.Presenter {

    private PostDetailsContract.View mPostDetailsView;

    private Disposable mDisposable;
    private ApiService mApiService;

    @Inject
    public PostDetailsPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void takeView(PostDetailsContract.View view) {
        mPostDetailsView = view;
    }

    @Override
    public void dropView() {
        mPostDetailsView = null;
    }
}
