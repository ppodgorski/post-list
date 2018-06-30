package com.ppodgorski.postlist.view.postdetails;

import com.ppodgorski.postlist.di.scope.ActivityScoped;
import com.ppodgorski.postlist.view.provider.IntentProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module
public abstract class PostDetailsModule {

    @ActivityScoped
    @Binds
    abstract PostDetailsContract.Presenter postDetailsPresenter(PostDetailsPresenter presenter);

    @Provides
    @ActivityScoped
    static Integer providePostId(PostDetailsActivity activity) {
        return activity.getIntent().getIntExtra(IntentProvider.POST_ID, 0);
    }

}
