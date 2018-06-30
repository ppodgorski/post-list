package com.ppodgorski.postlist.di.module;

import com.ppodgorski.postlist.view.postlist.PostListActivity;
import com.ppodgorski.postlist.di.scope.ActivityScoped;
import com.ppodgorski.postlist.view.postlist.PostListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = PostListModule.class)
    abstract PostListActivity postListActivity();

}
