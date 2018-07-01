package com.ppodgorski.postdetails;


import com.ppodgorski.RxSchedulersOverrideRule;
import com.ppodgorski.TestDataFactory;
import com.ppodgorski.postlist.model.User;
import com.ppodgorski.postlist.network.ApiService;
import com.ppodgorski.postlist.view.postdetails.PostDetailsContract;
import com.ppodgorski.postlist.view.postdetails.PostDetailsPresenter;

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
public class PostDetailsPresenterTest {

    @Rule
    public RxSchedulersOverrideRule schedulersOverrideRule = new RxSchedulersOverrideRule();

    private PostDetailsPresenter mPostDetailsPresenter;

    @Mock
    ApiService mApiService;

    @Mock
    PostDetailsContract.View mView;

    @Before
    public void setup() {
        mPostDetailsPresenter = new PostDetailsPresenter(mApiService, 0);
        mPostDetailsPresenter.takeView(mView);
    }

    @Test
    public void getData_whenStart_thenShowLoadingIndicatorAndHideDetailsContainer() {
        when(mApiService.getPost(0)).thenReturn(Observable.just(TestDataFactory.makePost()));

        mPostDetailsPresenter.getData();

        verify(mView).showLoadingIndicator();
        verify(mView).hideDetailsContainer();
    }

    @Test
    public void getData_whenSuccess_thenHideLoadingIndicator() {
        when(mApiService.getPost(0)).thenReturn(Observable.just(TestDataFactory.makePost()));

        mPostDetailsPresenter.getData();

        verify(mView).hideLoadingIndicator();
    }

    @Test
    public void getData_whenError_thenHideLoadingIndicator() {
        when(mApiService.getPost(0)).thenReturn(Observable.error(new RuntimeException()));

        mPostDetailsPresenter.getData();

        verify(mView).hideLoadingIndicator();
    }

    @Test
    public void getData_whenSuccess_thenShowDetailsContainer() {
        when(mApiService.getPost(0)).thenReturn(Observable.just(TestDataFactory.makePost()));
        when(mApiService.getUser(0)).thenReturn(Observable.just(new User()));
        when(mApiService.getPostComments(0)).thenReturn(Observable.just(Collections.emptyList()));

        mPostDetailsPresenter.getData();

        verify(mView).showDetailsContainer();
        verify(mView, never()).showError();
    }


    @Test
    public void getData_whenGetPostError_thenShowError() {
        when(mApiService.getPost(0)).thenReturn(Observable.error(new RuntimeException()));

        mPostDetailsPresenter.getData();

        verify(mView).showError();
        verify(mView, never()).showDetailsContainer();
    }

    @Test
    public void getData_whenGetUSerError_thenShowError() {
        when(mApiService.getPost(0)).thenReturn(Observable.just(TestDataFactory.makePost()));
        when(mApiService.getUser(0)).thenReturn(Observable.error(new RuntimeException()));

        mPostDetailsPresenter.getData();

        verify(mView).showError();
        verify(mView, never()).showDetailsContainer();
    }

    @Test
    public void getData_whenGetCommentsError_thenShowError() {
        when(mApiService.getPost(0)).thenReturn(Observable.just(TestDataFactory.makePost()));
        when(mApiService.getUser(0)).thenReturn(Observable.just(new User()));
        when(mApiService.getPostComments(0)).thenReturn(Observable.error(new RuntimeException()));

        mPostDetailsPresenter.getData();

        verify(mView).showError();
        verify(mView, never()).showDetailsContainer();
    }

    @After
    public void tearDown() {
        mPostDetailsPresenter.dropView();
    }
}
