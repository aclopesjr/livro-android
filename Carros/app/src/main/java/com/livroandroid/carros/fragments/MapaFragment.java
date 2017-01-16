package com.livroandroid.carros.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.livroandroid.carros.R;
import com.livroandroid.carros.domain.Carro;

import static com.livroandroid.carros.R.id.mapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        // Recupera o fragment que está no layout
        // Utiliza o getChildFragmentManager, pois é um fragment dentro do outro
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.mapFragment);

        // Inicia o Google Maps dentro do fragment
        mapFragment.getMapAsync(this);

        this.carro = (Carro)getArguments().getParcelable("carro");

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Método onMapReady é chamado quando a inicialização do mapa estiver ok
        this.googleMap = googleMap;
        if (this.carro != null) {
            // Ativa o botão para mostrar minha localização
            //googleMap.setMyLocationEnabled(true);

            // Cria o objeto latitude/longitude com a coordenada da fábrica
            LatLng location = new LatLng(Double.parseDouble(this.carro.latitude), Double.parseDouble(this.carro.longitude));

            // Posiciona o mapa na coordenada da fábrica (zoom 13)
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 13);
            this.googleMap.moveCamera(cameraUpdate);

            // Marcador no local da fábrica
            this.googleMap.addMarker(new MarkerOptions()
                    .title(this.carro.nome)
                    .snippet(this.carro.desc)
                    .position(location));

            // Tipo do mapa (normal, satélite, terreno ou híbrido)
            this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }
}
