package com.example.agendaappws;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {
    TextView tvBienvenida;
    TextView etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //enlaces
        tvBienvenida = (TextView) findViewById(R.id.tvBienvenida);
        etMensaje = (TextView) findViewById(R.id.etMensaje);
        //para el boton de back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent1 = getIntent();
        String nombre = intent1.getStringExtra("nombre");
        tvBienvenida.append(nombre);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
                finishAffinity();
            case R.id.cerrarsesion:
                this.logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("hanna");
        editor.apply();
        this.finish();
    }
}