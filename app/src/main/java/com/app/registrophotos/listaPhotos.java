package com.app.registrophotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.app.registrophotos.Configuraciones.SQLiteConexion;
import com.app.registrophotos.Configuraciones.Transacciones;
import com.app.registrophotos.Tablas.photograh;

import java.util.ArrayList;
import java.util.List;

public class listaPhotos extends AppCompatActivity {
    List<photograh> listPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_photos);
        initList();
    }

    public void initList(){
        listPhotos = new ArrayList<>();
        SQLiteConexion conex = new SQLiteConexion(this, Transacciones.DATE_BASE,null,1);
        SQLiteDatabase db = conex.getReadableDatabase();

        Cursor cursor = db.rawQuery(Transacciones.SELECT_TABLE_PHOTOS,null);

        while (cursor.moveToNext()){
            listPhotos.add(new photograh(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
        }
        cursor.close();
        db.close();

        AdapterListPhotos adp = new AdapterListPhotos(listPhotos,this);
        RecyclerView path = findViewById(R.id.recViewPhotos);
        path.setHasFixedSize(true);
        path.setLayoutManager(new LinearLayoutManager(this));
        path.setAdapter(adp);
    }
}