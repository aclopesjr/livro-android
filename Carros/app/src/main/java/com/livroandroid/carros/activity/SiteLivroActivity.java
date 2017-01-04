package com.livroandroid.carros.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.livroandroid.carros.R;
import com.livroandroid.carros.fragments.SiteLivroFragment;

public class SiteLivroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_livro);

        setupToolbar();

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.site_do_livro);

        if (savedInstanceState == null) {
            SiteLivroFragment frag = new SiteLivroFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }
}
