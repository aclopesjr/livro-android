package com.livroandroid.carros.domain;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.livroandroid.carros.R;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.HttpHelper;

/**
 * Created by Antonio on 09/01/2017.
 */

public class CarroService {

    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";

    public static List<Carro> getCarros(Context context, int tipo) throws IOException {
        String tipoString = getTipo(tipo);
        String url = URL.replace("{tipo}", tipoString);

        // Faz a requisição http
        HttpHelper httpHelper = new HttpHelper();
        String json = httpHelper.doGet(url);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        RootCar rootCar = gson.fromJson(json, RootCar.class);
        return rootCar.carros.listOfCarros;
    }

    private static String getTipo(int tipo) {
        switch (tipo) {
            case R.string.classicos:
                return "classicos";
            case R.string.esportivos:
                return "esportivos";
            default:
                return "luxo";
        }
    }
}
