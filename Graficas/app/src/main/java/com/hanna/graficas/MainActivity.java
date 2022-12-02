package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnBarras,btnBurbuja,btnTelaraña,btnPie,btnLinea,btnHorizontal,btnOndulada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBurbuja=(Button) findViewById(R.id.btnBarras);
        btnBarras = (Button) findViewById(R.id.btnBurbuja);
        btnTelaraña =(Button) findViewById(R.id.btnTelaraña);
        btnPie = (Button) findViewById(R.id.btnPie);
        btnLinea = (Button) findViewById(R.id.btnLinea);
        btnHorizontal = (Button) findViewById(R.id.btnHorizontal);
        btnOndulada = (Button) findViewById(R.id.btnOndulada);

        btnBarras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), BarrasActivity.class);
                startActivity(intent1);
            }
        });

        btnBurbuja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), BurbujasActivity.class);
                startActivity(intent2);
            }
        });

        btnTelaraña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(),TelarActivity.class);
                startActivity(intent3);
            }
        });

        btnPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(),PieActivity.class);
                startActivity(intent4);
            }
        });

        btnLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getApplicationContext(),LineaActivity.class);
                startActivity(intent5);
            }
        });
        btnHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(getApplicationContext(),BarrasHActivity.class);
                startActivity(intent6);
            }
        });

        btnOndulada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(getApplicationContext(),OnduladaActivity.class);
                startActivity(intent7);
            }
        });
    }
}