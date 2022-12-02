package com.example.menuoperaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSuma;
    Button btnDivision;
    Button btnRCuadrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlaces
        btnSuma = (Button) findViewById(R.id.btnSuma);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnRCuadrada = (Button) findViewById(R.id.btnRCuadrada);

        //oyentes
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirSuma();
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirDivision(); }
        });

        btnRCuadrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirRCuadrada(); }
        });
    }

    private void abrirSuma(){
        Intent intent1 = new  Intent(getApplicationContext(), SumaActivity.class);
        startActivity(intent1);
    }

    private void abrirDivision(){
        Intent intent2 = new Intent(getApplicationContext(), DivisionActivity.class);
        startActivity(intent2);
    }

    private void abrirRCuadrada(){
        Intent intent3 = new Intent(getApplicationContext(), RCuadradaActivity.class);
        startActivity(intent3);
    }
}