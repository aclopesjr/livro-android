package com.livroandroid.carros.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.livroandroid.carros.R;
import com.livroandroid.carros.fragments.CarrosFragment;

public class CarrosActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carros);

        // Configura a toolbar
        setupToolbar();

        // Mostra o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Mostra o tipo do carro
        String tipo = getString(getIntent().getIntExtra("tipo", 0));
        getSupportActionBar().setTitle(tipo);

        // Adiciona o fragment com o mesmo Bundle (args) da intent
        if (savedInstanceState == null) {
            // Cria uma instância do fragment e configura os argumentos
            CarrosFragment frag = new CarrosFragment();
            // Dentre os argumentos que foram passados para a activity, está o tipo do carro
            frag.setArguments(getIntent().getExtras());
            // Adiciona o fragment no layout de marcação
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }
}
