package com.example.mysharepreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnAcceder;
    EditText etUsuario, etPassword;
    RadioButton RBsesion;

    private boolean isActivateRadioButton;
    private static final String STRING_PREFERENCES = "hanna";
    private static final String PREFERENCE_ESTADO_BUTTON_SESION = "estado.button.sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ObtenerEstadoButton()){
            Intent intent1 = new Intent(getApplicationContext(),PrincipalActivity2.class);
            startActivity(intent1);
        }
        //enlaces
        btnAcceder = (Button) findViewById(R.id.btnAcceder);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
        RBsesion = (RadioButton) findViewById(R.id.RBsesion);

        isActivateRadioButton = RBsesion.isChecked();

        //oyentes
        RBsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isActivateRadioButton) {
                    RBsesion.setChecked(false);
                }
                isActivateRadioButton = RBsesion.isChecked();
            }
        });
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Acceder();
            }
        });
        CrearSharedPreferences();

    }
    public void guardarEstadoButton(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,RBsesion.isChecked()).apply();
    }
    public boolean ObtenerEstadoButton() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION,false);
    }
    private void Acceder(){
        guardarEstadoButton();
        String u = etUsuario.getText().toString();
        String p = etPassword.getText().toString();

        if (u.isEmpty() || p.isEmpty())
            Toast.makeText(getApplicationContext(), "dejastes campos vacios", Toast.LENGTH_LONG).show();
        else {
            SharedPreferences sp1 = getSharedPreferences("Mispreferencias",Context.MODE_PRIVATE);

            String usuario = sp1.getString("usuario", "no se encontro");
            String password = sp1.getString("password", "no se encontro");
            if (u.equals(usuario) && p.equals(password)){
                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(),PrincipalActivity2.class);
                startActivity(intent1);
            }
            else
            Toast.makeText(getApplicationContext(), "datos erroneos",
                    Toast.LENGTH_LONG).show();
        }
    }
    private void CrearSharedPreferences(){
        SharedPreferences prefer1 = getSharedPreferences("Mispreferencias",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer1.edit();
        editor.putString("usuario","hanna");
        editor.putString("password","123456");
        editor.commit();
    }
}