package com.ppodgorski.postlist.view.postdetails;

import com.ppodgorski.postlist.network.ApiService;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.ppodgorski.postlist.utils.RxTransformers.androidIO;

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
        mCompositeDisposable.add(mApiService.getPost(mPostId).compose(androidIO())
                .doOnNext(post -> mPostDetailsView.setupPostViews(post))
                .flatMap(post -> mApiService.getUser(post.getUserId()).compose(androidIO()))
                .doOnNext(user -> mPostDetailsView.setupUserViews(user))
                .flatMap(user -> mApiService.getPostComments(mPostId).compose(androidIO()))
                .doOnNext(comments -> mPostDetailsView.setupCommentsViews(comments))
                .doOnSubscribe(__ -> mPostDetailsView.showLoadingIndicator())
                .doOnSubscribe(__ -> mPostDetailsView.hideDetailsContainer())
                .doOnTerminate(() -> mPostDetailsView.hideLoadingIndicator())
                .subscribe(d -> mPostDetailsView.showDetailsContainer(), t -> mPostDetailsView.showError()));
    }

}
