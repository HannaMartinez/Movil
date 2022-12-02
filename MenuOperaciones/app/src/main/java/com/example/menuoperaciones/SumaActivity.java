package com.example.menuoperaciones;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SumaActivity extends AppCompatActivity {
    EditText etNumero1, etNumero2;
    Button btnCalcular;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNumero1 = (EditText) findViewById(R.id.etNumero1);
        etNumero2 = (EditText) findViewById(R.id.etNumero2);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void calcularSuma() {
        double n1 = Double.parseDouble(etNumero1.getText().toString());
        double n2 = Double.parseDouble(etNumero2.getText().toString());
        double res = n1 + n2;
        tvResultado.setText("El resultado es: "+res);
    }
}