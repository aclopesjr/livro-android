package com.livroandroid.carros.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.livroandroid.carros.CarrosApplication;
import com.livroandroid.carros.R;
import com.livroandroid.carros.domain.Carro;
import com.livroandroid.carros.domain.CarroDB;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarroFragment extends BaseFragment {

    private Carro carro;
    private FloatingActionButton floatingActionButton;

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

        floatingActionButton = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask("favoritos", taskFavoritar());
            }
        });

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Verifica se o carro está favoritado e troca a cor do bota FAB
        startTask("favoritos", checkFavorito());
    }

    private TaskListener checkFavorito() {
        return new BaseTask<Boolean>() {
            @Override
            public Boolean execute() throws Exception {
                CarroDB carroDB = new CarroDB(getContext());
                return carroDB.exists(carro.nome);
            }

            @Override
            public void updateView(Boolean response) {
                setFabColor(response);
            }
        };
    }

    private TaskListener taskFavoritar() {
        return new BaseTask<Boolean>() {
            @Override
            public Boolean execute() throws Exception {
                CarroDB carroDB = new CarroDB(getContext());
                if (!carroDB.exists(carro.nome)) {
                    carroDB.save(carro);
                    return true;
                } else {
                    carroDB.delete(carro);
                    return false;
                }
            }

            @Override
            public void updateView(Boolean response) {
                if (response) {
                    snack(getView(), "Carro adicionado aos favoritos!");
                } else {
                    snack(getView(), "Carro removido dos favoritos!");
                }
                setFabColor(response);

                CarrosApplication.getInstance().getBus().post("refresh");
            }
        };
    }

    private void setFabColor(Boolean favorito) {
        if (favorito) {
            floatingActionButton.setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.accent));
            floatingActionButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.yellow));
        } else {
            floatingActionButton.setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.accent));
            floatingActionButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.gray));
        }
    }
}
