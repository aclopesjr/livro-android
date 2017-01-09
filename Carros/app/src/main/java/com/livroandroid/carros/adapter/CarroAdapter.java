package com.livroandroid.carros.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import com.livroandroid.carros.domain.Carro;
import com.livroandroid.carros.R;

import java.util.List;

/**
 * Created by aclopesjr on 1/9/17.
 */

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarrosViewHolder> {
    protected static final String TAG = "livroandroid";
    private final Context context;

    // Lista de carros
    private final List<Carro> carros;

    // Interface de callback para expor os eventos de toque da lista
    private CarroOnClickListener carroOnClickListener;

    // Construtor, geralmente recebe o centext, a lista e a implementação da interface de callback
    public CarroAdapter(Context context, List<Carro> carros, CarroOnClickListener carroOnClickListener) {
        this.context = context;
        this.carros = carros;
        this.carroOnClickListener = carroOnClickListener;
    }

    @Override
    public int getItemCount() {
        // Retorna a quantidade de linhas da lista (geralmente é quantidade de elementos do array)
        return this.carros != null ? this.carros.size()  : 0;
    }

    @Override
    public CarroAdapter.CarrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Este método de inflar a view e criar um ViewHolder
        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_carro, viewGroup, false);

        // Depois de inflar a view, cria o ViewHolder
        // ViewHolder contém a referência para todas as views do layout
        CarrosViewHolder holder = new CarrosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CarrosViewHolder holder, final int position) {
        // Recupera o carro da lina x
        Carro c = this.carros.get(position);

        // Faz a ligação das informações do carro, com as views do layout
        holder.textView.setText(c.nome);
        holder.progressBar.setVisibility(View.VISIBLE);

        // Faz o download da foto e mostra o ProgressBar
        Picasso.with(this.context).load(c.urlFoto).fit().into(holder.imageView, new
                Callback() {
                    @Override
                    public void onSuccess() {
                        // Download Ok
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        //
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

        // Click
        if (this.carroOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Ao clicar na view da lista, chama a interface de callback
                    // Obs.: A variável position é final
                    carroOnClickListener.onClickCarro(holder.itemView, position);
                }
            });
        }
    }

    // Interface de callback para expor os eventos da lista
    public interface CarroOnClickListener {
        public void onClickCarro(View view, int idx);
    }

    // O ViewHolder possui a referência para a sviews do layout
    public static  class CarrosViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        ImageView imageView;
        ProgressBar progressBar;
        CardView cardView;

        public CarrosViewHolder(View view) {
            super(view);

            // Faz o findViewById para armazenar as views
            // O Android vai reutilizar este ViewHolder durante a rolagem
            textView = (TextView)view.findViewById(R.id.text);
            imageView = (ImageView)view.findViewById(R.id.img);
            progressBar = (ProgressBar)view.findViewById(R.id.progressImg);
            cardView = (CardView)view.findViewById(R.id.card_view);
        }
    }
}
