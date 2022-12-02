package com.bs.recyapp.activity.ui.Favoritos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bs.recyapp.R;

public class VisorImagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_imagen);

        ImageView img = (ImageView) findViewById(R.id.imgCompleta);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b!=null){
            img.setImageResource(b.getInt("IMG"));
        }

    }
}