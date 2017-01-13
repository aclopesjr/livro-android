package com.livroandroid.carros.domain;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Carro implements Parcelable {
    private static final long serialVersionUID = 6601006766832473959L;

    @SerializedName("id")
    public long id;

    @SerializedName("tipo")
    public String tipo;

    @SerializedName("nome")
    public String nome;

    @SerializedName("desc")
    public String desc;

    @SerializedName("url_foto")
    public String urlFoto;

    @SerializedName("url_info")
    public String urlInfo;

    @SerializedName("url_video")
    public String urlVideo;

    @SerializedName("latitude")
    public String latitude;

    @SerializedName("longitude")
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", urlInfo='" + urlInfo + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Escreve os dados para serem serializados
        dest.writeLong(id);
        dest.writeString(this.tipo);
        dest.writeString(this.nome);
        dest.writeString(this.desc);
        dest.writeString(this.urlFoto);
        dest.writeString(this.urlInfo);
        dest.writeString(this.urlVideo);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
    }

    public void readFromParcel(Parcel parcel) {
        // Lê os dados na mesma ordem em que foram escritos
        this.id = parcel.readLong();
        this.tipo = parcel.readString();
        this.nome = parcel.readString();
        this.desc = parcel.readString();
        this.urlFoto = parcel.readString();
        this.urlInfo = parcel.readString();
        this.urlVideo = parcel.readString();
        this.latitude = parcel.readString();
        this.longitude = parcel.readString();
    }

    public static final Parcelable.Creator<Carro> CREATOR = new Parcelable.Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel p) {
            Carro c = new Carro();
            c.readFromParcel(p);
            return c;
        }

        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };
}
