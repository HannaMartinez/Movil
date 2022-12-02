package com.example.logingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etnusuario, etnpassword;
    Button btnLoging, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permisos
        ComprobarPermisos();

        //Enlases
        etnusuario = (EditText) findViewById(R.id.etnusuario);
        etnpassword = (EditText) findViewById(R.id.etnpassword);
        btnLoging = (Button) findViewById(R.id.btnLoging);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        //Oyentes
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
        btnLoging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validar();
                }

        });
    }
    private void Validar(){
        String usuario = etnusuario.getText().toString();
        String password = etnpassword.getText().toString();
        String n = "Hanna";
        if(usuario.isEmpty()|| password.isEmpty())
            Toast.makeText(getApplicationContext(), "¡Debes llenar ambos campos!", Toast.LENGTH_LONG).show();
        else{
            if(usuario.equals("Hanna")&& password.equals("admin1234")){
                Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
                intent1.putExtra("nombre", n);
                startActivity(intent1);

            }
            else
                Toast.makeText(getApplicationContext(), "Usuario y/o password incorrectos", Toast.LENGTH_LONG).show();
        }
    }
    private void ComprobarPermisos(){
        List<String> permisos = new ArrayList<>();

        if (checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (checkCallingOrSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.CAMERA);

        if (checkCallingOrSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.READ_CONTACTS);

        if (checkCallingOrSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.WRITE_CONTACTS);

        if (permisos.size()>0)
            requestPermissions(permisos.toArray(new String[permisos.size()]), 123);
    }
}
