package com.livroandroid.carros.domain;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Carros {

    @SerializedName("carro")
    List<Carro> listOfCarros;
}
