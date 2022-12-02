package com.example.myshraredpreferences;

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
    EditText etUsuario;
    EditText etPassword;
    RadioButton RBsesion;

    private boolean isActivateRadioButton;
    private static final String STRING_PREFERENCES = "hanna";
    private static final String PREFERENCE_ESTADO_BUTTON_SESION = "estado.button.sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ObtenerEstadoButton()){
            Intent intent1 = new Intent(getApplicationContext(),PrincipalActivity.class);
            startActivity(intent1);

        //Enlazar los controladores con la vista
        btnAcceder = (Button) findViewById(R.id.btnAcceder);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);

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
                public void onClick(View view) { Acceder();
                }
            });


    public void guardarEstadoButton(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,RBsesion.isChecked()).apply();
    }
    public boolean ObtenerEstadoButton() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION,false);
    }
    private void Acceder(){
        String u = etUsuario.getText().toString();
        String p = etPassword.getText().toString();

        if (u.isEmpty() || p.isEmpty())
            Toast.makeText(getApplicationContext(),"Dejaste campos vacios.",
                    Toast.LENGTH_LONG).show();
        else {
            SharedPreferences sp1 = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
            String usuario = sp1.getString("usuario", "No se encontro");
            String password = sp1.getString("password", "No se encontro");
            if (u.equals(usuario) && p.equals(password)){
                Toast.makeText(getApplicationContext(), "Bienvenid@", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(),PrincipalActivity.class);
                startActivity(intent1);
            }
            else
                Toast.makeText(getApplicationContext(), "Datos erroneos", Toast.LENGTH_SHORT).show();
        }
    }
    private void CrearSharedPreferences() {
        SharedPreferences prefer1 = getSharedPreferences("MisPreferencias",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer1.edit();
        editor.putString("usuario","hanna");
        editor.putString("password","123456");
        editor.commit();
    }
}