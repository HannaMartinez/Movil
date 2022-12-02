package com.example.menuoperaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RCuadradaActivity extends AppCompatActivity {
    EditText etNumero1;
    Button btnCalcular;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcuadrada);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNumero1 = (EditText) findViewById(R.id.etNumero1);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calcularRCuadrada();    
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No puedes tener campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void calcularRCuadrada(){
        String num1 = etNumero1.getText().toString();
        int k1 = Integer.parseInt(num1);
        if(k1 < 0) {
            etNumero1.setError("No puedes ingresar numeros negativos");
        } else if(k1>0) {
            double n1 = Double.parseDouble(etNumero1.getText().toString());
            double res = Math.sqrt(n1);
            tvResultado.setText("El resultado es : " + res);
        } else if (k1 == 0){
            tvResultado.setText(("El resultado es: 0"));
        }

    }
}