package com.livroandroid.carros.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.livroandroid.carros.CarrosApplication;
import com.livroandroid.carros.R;
import com.livroandroid.carros.activity.CarroActivity;
import com.livroandroid.carros.adapter.CarroAdapter;
import com.livroandroid.carros.domain.Carro;
import com.livroandroid.carros.domain.CarroService;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.List;

import livroandroid.lib.utils.AndroidUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarrosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarrosFragment extends BaseFragment {

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

        // Registra a classe para receber eventos
        CarrosApplication.getInstance().getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        CarrosApplication.getInstance().getBus().unregister(this);
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
        new GetCarrosTask().execute();
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

    private class GetCarrosTask extends AsyncTask<Void, Void, List<Carro>> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), "Atualizando", "Por favor, aguarde!", false, true);
        }

        @Override
        protected List<Carro> doInBackground(Void... voids) {
            try {
                return CarroService.getCarros(getContext(), tipo);
            } catch (IOException e) {
                Log.e("livro", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Carro> carros) {
            if (carros != null) {
                CarrosFragment.this.carros = carros;
                recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
                progressDialog.dismiss();
            }
        }
    }

    @Subscribe
    public void onBusAtualizarListaCarros(String refresh) {
        taskCarros();
    }
}
