package com.livroandroid.carros.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.livroandroid.carros.R;
import com.livroandroid.carros.adapter.TabsAdapter;

import livroandroid.lib.utils.Prefs;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupNavDrawer();
        setupViewPagerTabs();

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snack(view, "Exemplo de FAB button!");
            }
        });
    }

    private void setupViewPagerTabs() {
        // ViewPager
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getContext(), getSupportFragmentManager()));

        // Lê o índice da última tab utilizada no aplicativo
        int index = Prefs.getInteger(getContext(), "index");
        viewPager.setCurrentItem(index);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                Prefs.setInteger(getContext(), "index", viewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        // Tabs
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        // Cria as tabs com o mesmo adapter utilizado pelo ViewPager
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(), R.color.white);
        tabLayout.setTabTextColors(cor, cor);
    }
}
