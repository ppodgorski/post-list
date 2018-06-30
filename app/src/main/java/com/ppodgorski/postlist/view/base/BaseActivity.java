package com.ppodgorski.postlist.view.base;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.ppodgorski.postlist.R;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected void showNetworkError() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                R.string.network_error, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

}
