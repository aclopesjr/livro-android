package com.livroandroid.carros.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.R.attr.id;
import static com.livroandroid.carros.R.string.nome;

/**
 * Created by Antonio on 13/01/2017.
 */

public class CarroDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";

    // Nome do banco
    public static final String NOME_BANCO = "livro_android.sqlite";
    private static final int VERSAO_BANCO = 1;

    public CarroDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Criando a Tabela...");
        sqLiteDatabase.execSQL("create table if not exists carro (_id integer primary key autoincrement, nome text, desc text, url_foto text, url_video text, latitude text, longitude text, tipo text);");
        Log.d(TAG, "Tabela carro criada com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long save(Carro carro) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", carro.nome);
            values.put("desc", carro.desc);
            values.put("url_foto", carro.urlFoto);
            values.put("url_video", carro.urlVideo);
            values.put("latitude", carro.latitude);
            values.put("longitude", carro.longitude);
            values.put("tipo", carro.tipo);
            if (carro.id != 0) {
                String id = String.valueOf(carro.id);
                String[] whereArgs = new String[] {id};

                int count = db.update("carro", values, "_id=?", whereArgs);
                return count;
            } else {
                long id = db.insert("carro", "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    public int delete(Carro carro) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            String[] whereArgs = new String[] {String.valueOf(carro.id)};
            int id = db.delete("carro", "_id=?", whereArgs);
            return id;
        } finally {
            db.close();
        }
    }

    public List<Carro> findAll() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cursor = db.query("carro", null, null, null, null, null, null, null);
            return toList(cursor);
        } finally {
            db.close();
        }
    }

    private List<Carro> toList(Cursor cursor) {
        List<Carro> carros = new ArrayList<Carro>();
        if (cursor.moveToFirst()) {
            do {
                Carro carro = new Carro();
                carro.id = cursor.getLong(cursor.getColumnIndex("_id"));
                carro.nome = cursor.getString(cursor.getColumnIndex("nome"));
                carro.desc = cursor.getString(cursor.getColumnIndex("desc"));
                carro.urlFoto = cursor.getString(cursor.getColumnIndex("url_foto"));
                carro.urlVideo = cursor.getString(cursor.getColumnIndex("url_video"));
                carro.longitude = cursor.getString(cursor.getColumnIndex("longitude"));
                carro.latitude = cursor.getString(cursor.getColumnIndex("latitude"));
                carro.tipo = cursor.getString(cursor.getColumnIndex("tipo"));

                carros.add(carro);
            } while (cursor.moveToNext());
        }
        return carros;
    }

    public boolean exists(String nome) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cursor = db.query("carro", null, "nome=?", new String[]{nome}, null, null, null, null);
            return cursor.getCount() > 0;
        } finally {
            db.close();
        }
    }

    public void ExecSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql);
        } finally {
            db.close();
        }
    }

    public void ExecSQL(String sql, Objects[] args) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql, args);
        } finally {
            db.close();
        }
    }
}
