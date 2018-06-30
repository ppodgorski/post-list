package com.ppodgorski.postlist.view.postlist;

import com.ppodgorski.postlist.view.base.BasePresenter;
import com.ppodgorski.postlist.view.base.BaseView;

public interface PostListContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }

}
