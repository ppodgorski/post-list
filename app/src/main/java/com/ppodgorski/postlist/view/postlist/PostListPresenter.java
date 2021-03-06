package com.ppodgorski.postlist.view.postlist;


import com.ppodgorski.postlist.di.scope.ActivityScoped;
import com.ppodgorski.postlist.network.ApiService;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.ppodgorski.postlist.utils.RxTransformers.androidIO;

@ActivityScoped
public class PostListPresenter implements PostListContract.Presenter {

    private PostListContract.View mPostListView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ApiService mApiService;

    @Inject
    public PostListPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void takeView(PostListContract.View view) {
        mPostListView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mPostListView = null;
    }

    @Override
    public void getPosts() {
        mCompositeDisposable.add(mApiService.getPosts()
                .compose(androidIO())
                .doOnSubscribe(__ -> mPostListView.showLoadingIndicator())
                .doOnSubscribe(__ -> mPostListView.hidePosts())
                .doOnTerminate(() -> mPostListView.hideLoadingIndicator())
                .subscribe(mPostListView::showPosts, t -> mPostListView.showError()));
    }


}
