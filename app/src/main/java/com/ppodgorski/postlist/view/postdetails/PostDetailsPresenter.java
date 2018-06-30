package com.ppodgorski.postlist.view.postdetails;

import com.ppodgorski.postlist.network.ApiService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PostDetailsPresenter implements PostDetailsContract.Presenter {

    private PostDetailsContract.View mPostDetailsView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ApiService mApiService;
    private Integer mPostId;

    @Inject
    public PostDetailsPresenter(ApiService apiService, Integer postId) {
        mApiService = apiService;
        mPostId = postId;
    }

    @Override
    public void takeView(PostDetailsContract.View view) {
        mPostDetailsView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mPostDetailsView = null;
    }

    @Override
    public void getData() {
        mCompositeDisposable.add(mApiService.getPost(mPostId)
                .doOnNext(post -> mPostDetailsView.setupPostViews(post))
                .flatMap(post -> mApiService.getUser(post.getUserId()))
                .doOnNext(user -> mPostDetailsView.setupUserViews(user))
                .flatMap(user -> mApiService.getPostComments(mPostId))
                .doOnNext(comments -> mPostDetailsView.setupCommentsViews(comments))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> mPostDetailsView.showLoadingIndicator())
                .doOnSubscribe(__ -> mPostDetailsView.hideDetailsContainer())
                .doOnTerminate(() -> mPostDetailsView.hideLoadingIndicator())
                .subscribe(d -> mPostDetailsView.showDetailsContainer(), t -> mPostDetailsView.showError()));
    }

}
