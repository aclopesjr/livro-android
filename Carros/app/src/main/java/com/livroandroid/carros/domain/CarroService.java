package com.livroandroid.carros.domain;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.livroandroid.carros.R;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import livroandroid.lib.utils.FileUtils;

/**
 * Created by Antonio on 09/01/2017.
 */

public class CarroService {
    public static List<Carro> getCarros(Context context, int tipo) {
        String tipoString = context.getString(tipo);
//        List<Carro> carros = new ArrayList<Carro>();
//        for (int i = 0; i < 20; i++) {
//            Carro c = new Carro();
//            c.nome = "Carro " + tipoString + ": " + i;
//            c.desc = "Desc " + i;
//            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";
//            carros.add(c);
//        }
        String json = readFile(context, tipo);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        RootCar rootCar = gson.fromJson(json, RootCar.class);
        return rootCar.carros.listOfCarros;
    }

    private static String readFile(Context context, int tipo) {
        try {
            switch (tipo) {
                case R.string.classicos :
                    return FileUtils.readRawFileString(context, R.raw.carros_classicos, "UTF-8");
                case R.string.esportivos :
                    return FileUtils.readRawFileString(context, R.raw.carros_esportivos, "UTF-8");
                default:
                    return FileUtils.readRawFileString(context, R.raw.carros_luxo, "UTF-8");
            }
        } catch (IOException e) {
            return "";
        }
    }
}
