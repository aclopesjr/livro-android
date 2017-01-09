package com.livroandroid.carros.activity;

import android.os.Bundle;

import com.livroandroid.carros.R;
import com.livroandroid.carros.domain.Carro;
import com.livroandroid.carros.fragments.CarroFragment;

public class CarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        // Configuração a Toolbar como a action bar
        setupToolbar();

        // Título da Toolbar como a action bar
        Carro carro = (Carro)getIntent().getParcelableExtra("carro");
        getSupportActionBar().setTitle(carro.nome);

        // Liga o botão up navigation para voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Cria o fragment com mesmo bundle intent
            CarroFragment carroFragment = new CarroFragment();
            carroFragment.setArguments(getIntent().getExtras());

            // Adiciona o fragment no layout
            getSupportFragmentManager().beginTransaction().add(R.id.CarroFragment, carroFragment).commit();
        }
    }
}
