package com.example.logingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    TextView tvBienvenida;
    EditText etMensaje;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Activamos el boton back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //enlaces
        tvBienvenida = (TextView) findViewById(R.id.tvBienvenida);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        //Mostrar el mensaje de bienvenida
        Intent intent1 = getIntent();
        String nombre = intent1.getStringExtra("nombre");
        tvBienvenida.append(nombre);

        //Oyentes
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarMensaje();
            }
        });
    }
    private void EnviarMensaje(){

        String mensaje = etMensaje.getText().toString();
        if (mensaje.isEmpty())
            Toast.makeText(getApplicationContext(), "El mensaje no debe estar vacio", Toast.LENGTH_LONG).show();
        else {
            Intent intent2 = new Intent();
            intent2.setAction(Intent.ACTION_SEND);
            intent2.putExtra(Intent.EXTRA_TEXT, mensaje);
            intent2.setType("text/plain");


            Intent intent3 = Intent.createChooser(intent2, null);
            startActivity(intent3);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
