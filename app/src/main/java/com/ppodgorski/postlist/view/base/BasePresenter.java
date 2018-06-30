package com.ppodgorski.postlist.view.base;

public interface BasePresenter<T> {
    void takeView(T view);
    void dropView();
}
