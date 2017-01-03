package com.livroandroid.carros.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.livroandroid.carros.R;

/**
 * Created by Antonio on 03/01/2017.
 */

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
}
