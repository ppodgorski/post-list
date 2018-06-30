package com.ppodgorski.postlist.view.postdetails;

import com.ppodgorski.postlist.di.scope.ActivityScoped;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class PostDetailsModule {

    @ActivityScoped
    @Binds
    abstract PostDetailsContract.Presenter postDetailsPresenter(PostDetailsPresenter presenter);

}
