package com.example.sumadoraapp4951;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Paso 1: declaro los objetos locales para despúes enlazarlos con los controladores de la vista
    EditText etNumero1, etNumero2;
    Button btnCalcular;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Paso 2: Enlazo los objetos locales con los controladores mediante R
        etNumero1 = (EditText) findViewById(R.id.etNumero1);
        etNumero2 = (EditText) findViewById(R.id.etNumero2);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        //Paso 3: Programo los oyentes de los eventos de la activity
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calcularSuma();
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No puedes tener campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void calcularSuma() {
        double n1 = Double.parseDouble(etNumero1.getText().toString());
        double n2 = Double.parseDouble(etNumero2.getText().toString());
        double res = n1 + n2;
        tvResultado.setText("El resultado es: "+res);
    }
}