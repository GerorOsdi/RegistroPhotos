package com.app.registrophotos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.registrophotos.Configuraciones.SQLiteConexion;
import com.app.registrophotos.Configuraciones.Transacciones;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView viewPhoto;
    ImageButton SendPhoto;
    Bitmap IMG;
    EditText descrip;
    Button btnSendSQL, btnListPhotos;
    static final int REQUEST_IMAGE_CAPTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPhoto = (ImageView) findViewById(R.id.viewPhoto);
        SendPhoto = (ImageButton) findViewById(R.id.btnSendPhoto);
        descrip = (EditText) findViewById(R.id.txtDescripcion);
        btnSendSQL = (Button) findViewById(R.id.btnGuardarSQL); 
        btnListPhotos = (Button) findViewById(R.id.btnListPhotos);

        SendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapIMG();
            }
        });

        btnSendSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPhoto.getDrawable() != null && !descrip.getText().toString().isEmpty()){
                 RegistroPhoto();
                }else{
                    Toast.makeText(MainActivity.this, "Ha dejado campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnListPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),listaPhotos.class);
                startActivity(intent);

            }
        });
    }

    private void CapIMG() {
        Intent intGaleria = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intGaleria.resolveActivity(getPackageManager()) != null) {
        startActivityForResult(intGaleria,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE){
            Bundle datEx = data.getExtras();
             IMG = (Bitmap) datEx.get("data");
            viewPhoto.setImageBitmap(IMG);

        }
    }

    private String getPhotoString(Bitmap phot){
        try {
            ByteArrayOutputStream arrayBa = new ByteArrayOutputStream();
            phot.compress(Bitmap.CompressFormat.JPEG,100,arrayBa);

            byte[] photoByte = arrayBa.toByteArray();

            String encode = Base64.encodeToString(photoByte, Base64.DEFAULT);

            return encode;

        }catch (Exception ex){
            ex.toString();
        }
        return "";
    }

    private void RegistroPhoto(){
        SQLiteConexion conex = new SQLiteConexion(this, Transacciones.DATE_BASE,null,1);
        SQLiteDatabase db = conex.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put(Transacciones.IMAGE,getPhotoString(IMG));
        datos.put(Transacciones.DESCRIPCION,descrip.getText().toString());

        Long resp = db.insert(Transacciones.TABLE_PHOTO,Transacciones.ID,datos);

        if (resp > 0){
            Toast.makeText(this, "#Reg: "+resp.toString()+" con exito!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Error al realizar el registro", Toast.LENGTH_SHORT).show();
        }
    }
}