package com.livroandroid.carros.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.livroandroid.carros.R;
import com.livroandroid.carros.domain.Carro;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarroFragment extends BaseFragment {

    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla a view deste fragment
        View view = inflater.inflate(R.layout.fragment_carro, container, false);

        // Lê o objeto carro dos parâmetros
        carro = (Carro)getArguments().getParcelable("carro");

        // Atualiza a descrição do carro no TextView
        TextView textView = (TextView)view.findViewById(R.id.tDesc);
        textView.setText(carro.desc);

        return  view;
    }

}
