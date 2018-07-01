package com.ppodgorski.postlist;

import com.ppodgorski.RxSchedulersOverrideRule;
import com.ppodgorski.postlist.network.ApiService;
import com.ppodgorski.postlist.view.postlist.PostListContract;
import com.ppodgorski.postlist.view.postlist.PostListPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PostListPresenterTest {

    @Rule
    public RxSchedulersOverrideRule schedulersOverrideRule = new RxSchedulersOverrideRule();

    private PostListPresenter mPostListPresenter;

    @Mock
    ApiService mApiService;

    @Mock
    PostListContract.View mView;

    @Before
    public void setup() {
        mPostListPresenter = new PostListPresenter(mApiService);
        mPostListPresenter.takeView(mView);
    }

    @Test
    public void getPosts_whenStart_thenShowLoadingIndicatorAndHidePosts() {
        when(mApiService.getPosts()).thenReturn(Observable.just(Collections.emptyList()));

        mPostListPresenter.getPosts();

        verify(mView).showLoadingIndicator();
        verify(mView).hidePosts();
    }

    @Test
    public void getPosts_whenSuccess_thenHideLoadingIndicator() {
        when(mApiService.getPosts()).thenReturn(Observable.just(Collections.emptyList()));

        mPostListPresenter.getPosts();

        verify(mView).hideLoadingIndicator();
    }

    @Test
    public void getPosts_whenError_thenHideLoadingIndicator() {
        when(mApiService.getPosts()).thenReturn(Observable.error(new RuntimeException()));

        mPostListPresenter.getPosts();

        verify(mView).hideLoadingIndicator();
    }

    @Test
    public void getPosts_whenSuccess_showPosts() {
        when(mApiService.getPosts()).thenReturn(Observable.just(Collections.emptyList()));

        mPostListPresenter.getPosts();

        verify(mView).showPosts(Collections.emptyList());
        verify(mView, never()).showError();
    }

    @Test
    public void getPosts_whenError_showError() {
        when(mApiService.getPosts()).thenReturn(Observable.error(new RuntimeException()));

        mPostListPresenter.getPosts();

        verify(mView).showError();
        verify(mView, never()).showPosts(Collections.emptyList());
    }

    @After
    public void tearDown() {
        mPostListPresenter.dropView();
    }

}
