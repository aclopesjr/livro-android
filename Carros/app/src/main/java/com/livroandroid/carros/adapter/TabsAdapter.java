package com.livroandroid.carros.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.livroandroid.carros.R;
import com.livroandroid.carros.fragments.CarrosFragment;

/**
 * Created by aclopesjr on 1/11/17.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    private Context context;

    public TabsAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return context.getString(R.string.classicos);
            case 1:
                return context.getString(R.string.esportivos);
            default:
                return context.getString(R.string.luxo);
        }
    }

    @Override
    public Fragment getItem(int position) {
        // Cria o fragment para cada página
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = CarrosFragment.newInstance(R.string.classicos);
                break;
            case 1:
                fragment = CarrosFragment.newInstance(R.string.esportivos);
                break;
            default:
                fragment = CarrosFragment.newInstance(R.string.luxo);
                break;
        }
        return fragment;
    }
}
