package com.ppodgorski.postlist.view.postlist;

import android.support.annotation.Nullable;

import com.ppodgorski.postlist.di.scope.ActivityScoped;
import com.ppodgorski.postlist.model.Post;
import com.ppodgorski.postlist.network.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@ActivityScoped
public class PostListPresenter implements PostListContract.Presenter {

    private PostListContract.View mPostListView;

    private Disposable mDisposable;
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
        mDisposable.dispose();
        mPostListView = null;
    }

    @Override
    public void getPosts() {
        mPostListView.showLoadingIndicator();
        mDisposable = mApiService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> mPostListView.showLoadingIndicator())
                .doOnTerminate(() -> mPostListView.hideLoadingIndicator())
                .subscribe(mPostListView::showPosts, t -> mPostListView.showError());
    }


}
