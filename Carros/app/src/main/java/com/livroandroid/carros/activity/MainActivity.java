package com.livroandroid.carros.activity;

import android.os.Bundle;

import com.livroandroid.carros.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupNavDrawer();
    }
}
