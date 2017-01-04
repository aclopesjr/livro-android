package com.livroandroid.carros.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.livroandroid.carros.R;

import static com.livroandroid.carros.R.string.nome;

/**
 * Created by Antonio on 03/01/2017.
 */

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {
    protected DrawerLayout drawerLayout;

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected void setupNavDrawer() {
        //Drawer Layout
        final ActionBar actionBar = getSupportActionBar();

        //Ícone do menu Nav Drawer
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);

        if (drawerLayout != null && navigationView != null) {
            // Atualiza a imagem e os textos do header do menu lateral
            setNavViewValues(navigationView, R.string.nav_drawer_username, R.string.nav_drawer_email, R.mipmap.ic_launcher);

            // Trata o evento de clique do menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    // Seleciona a linha
                    menuItem.setChecked(true);

                    // Fecha o menu
                    drawerLayout.closeDrawers();

                    // Trata o evento de menu
                    onNavDrawerItemSelected(menuItem);

                    return true;
                }
            });
        }
    }

    public static void setNavViewValues(NavigationView navigationView, int nome, int email, int img) {
        View headerView = navigationView.getHeaderView(0);
        TextView tNome = (TextView)headerView.findViewById(R.id.tNome);
        tNome.setText(nome);

        TextView tEmail = (TextView)headerView.findViewById(R.id.tEmail);
        tEmail.setText(email);

        ImageView tImage = (ImageView)headerView.findViewById(R.id.img);
        tImage.setImageResource(img);
    }

    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_carros_todos :
                toast("Clicou em carros");
                break;
            case R.id.nav_item_carros_classicos :
                Intent intent = new Intent(getContext(), CarrosActivity.class);
                intent.putExtra("tipo", R.string.classicos);
                startActivity(intent);
                break;
            case R.id.nav_item_carros_esportivos :
                intent = new Intent(getContext(), CarrosActivity.class);
                intent.putExtra("tipo", R.string.esportivos);
                startActivity(intent);
                break;
            case  R.id.nav_item_carros_luxo :
                intent = new Intent(getContext(), CarrosActivity.class);
                intent.putExtra("tipo", R.string.luxo);
                startActivity(intent);
                break;
            case R.id.nav_item_site_livro :
                startActivity(new Intent(getContext(), SiteLivroActivity.class));
                break;
            case R.id.nav_item_settings :
                toast("Clicou em configurações");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                // Trata o clique no botão que abre o menu
                if (drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
