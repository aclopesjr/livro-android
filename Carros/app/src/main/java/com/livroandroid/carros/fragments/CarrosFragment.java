package com.livroandroid.carros.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livroandroid.carros.R;
import com.livroandroid.carros.activity.CarroActivity;
import com.livroandroid.carros.adapter.CarroAdapter;
import com.livroandroid.carros.domain.Carro;
import com.livroandroid.carros.domain.CarroService;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarrosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarrosFragment extends BaseFragment {

    private ProgressDialog progressDialog;
    protected RecyclerView recyclerView;

    // Tipo de carro passado pelos argumentos
    private int tipo;

    // Lista de carros
    private List<Carro> carros;

    public static CarrosFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);

        CarrosFragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.tipo = getArguments().getInt("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros, container, false);

        this.recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros();
    }



    private void taskCarros() {

        progressDialog = ProgressDialog.show(getActivity(), "Atualizando", "Por favor, aguarde!", false, true);
        new Thread() {
            @Override
            public void run() {
                try {
                    // Busca os carros pelo tipo
                    carros = CarroService.getCarros(getContext(), tipo);

                    // Atualiza a lista na UI Thread
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // É aqui que utiliza o adapter. O adapeter fornece o conteúdo para a lista
                            recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));

                            progressDialog.dismiss();
                        }
                    });
                } catch (IOException e) {
                    Log.e("livro", e.getMessage(), e);
                } finally {

                }
            }
        }.start();
    }

    private CarroAdapter.CarroOnClickListener onClickCarro() {
        return new CarroAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {
                // Carro selecionado
                Carro carro = carros.get(idx);

                Intent intent = new Intent(getContext(), CarroActivity.class);
                intent.putExtra("carro", carro);
                startActivity(intent);
            }
        };
    }
}
