package com.ppodgorski.postlist.view.postlist;

import com.ppodgorski.postlist.di.scope.ActivityScoped;
import com.ppodgorski.postlist.view.postlist.recycler.PostClickListener;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class PostListModule {

    @ActivityScoped
    @Binds
    abstract PostListContract.Presenter postListPresenter(PostListPresenter presenter);

    @ActivityScoped
    @Binds
    abstract PostClickListener postClickListener(PostListActivity activity);
}
