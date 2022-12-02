package com.example.proveedorcontenido;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnCargar;
    Button btnAgregar;
    TextView tvResultados;
    private static final int PICK_CONTACT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //comprobar permisos

        ComprobarPermisos();
        //enlaces
        btnCargar = (Button) findViewById(R.id.btnCargar);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        tvResultados =(TextView) findViewById(R.id.tvResultado);
        //oyente
        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultarContactos();
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearContactos();
            }
        });
    }
    private void ConsultarContactos(){
        Intent intent1 =new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent1,PICK_CONTACT);
    }
    private void CrearContactos(){
        Intent intent2 = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent2, PICK_CONTACT);
    }

    private void ComprobarPermisos() {
        ArrayList<String> permisos = new ArrayList<>();
        if(getApplicationContext().checkCallingPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        if(getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.WRITE_CONTACTS);
        if(getApplicationContext().checkSelfPermission(Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.READ_CONTACTS);
        if(permisos.size()>0)
            requestPermissions(permisos.toArray(new String[permisos.size()]),123);

    }
    public void onActivityResult(int reqcode, int resultcode, Intent data) {

        super.onActivityResult(reqcode, resultcode, data);
        tvResultados.setText("");
        if(reqcode == PICK_CONTACT && resultcode == Activity.RESULT_OK){
            Uri datos = data.getData();
            Cursor cursor1 = managedQuery(datos, null, null, null, null);
            if(cursor1.moveToFirst()){
                String id= cursor1.getString(cursor1.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String nombre= cursor1.getString(cursor1.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                tvResultados.setText(id+", "+nombre+" " );
                try{
                    Cursor telefonos = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+id,null , null );
                    int t = telefonos.getCount();
                    telefonos.moveToFirst();
                    for (int i=0;i<t;i++){
                        String tel = telefonos.getString((telefonos.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA)));
                        tvResultados.append(tel);
                        telefonos.moveToNext();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}