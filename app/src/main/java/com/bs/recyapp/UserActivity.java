package com.bs.recyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity {
    private File f;
    private Button btnSubirImagen, btnGuardarDatos;
    private CircleImageView imageUser;
    private EditText edtNameUser, edtApellidoPaternoU, edtApellidoMaternoU, edtPasswordUser, edtEmailUser;
    private TextInputLayout txtInputNameUser, txtInputApellidoPaternoU, txtInputApellidoMaternoU,
            txtInputTelefonoU, txtInputEmailUser, txtInputPasswordUser;
    private final static int LOCATION_REQUEST_CODE = 23;

    String nombre,app,apm,tel,password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.init();

        btnGuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
    private void init(){
        btnGuardarDatos = (Button) findViewById(R.id.btnGuardarDatos);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);
        imageUser = findViewById(R.id.imageUser);
        edtNameUser = (EditText)     findViewById(R.id.edtNameUser);
        edtApellidoPaternoU = (EditText) findViewById(R.id.edtApellidoPaternoU);
        edtApellidoMaternoU = (EditText) findViewById(R.id.edtApellidoMaternoU);
        edtPasswordUser = (EditText) findViewById(R.id.edtPasswordUser);
        edtEmailUser = (EditText) findViewById(R.id.edtEmailUser);
        //TextInputLayout
        txtInputNameUser = findViewById(R.id.txtInputNameUser);
        txtInputApellidoPaternoU = findViewById(R.id.txtInputApellidoPaternoU);
        txtInputApellidoMaternoU = findViewById(R.id.txtInputApellidoMaternoU);
        txtInputEmailUser = findViewById(R.id.txtInputEmailUser);
        txtInputPasswordUser = findViewById(R.id.txtInputPasswordUser);
        btnSubirImagen.setOnClickListener(v -> {
            this.cargarImagen();
        });
    }

    private void cargarImagen() {
         Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         i.setType("image/");
         startActivityForResult(Intent.createChooser(i, "Seleccione la Aplicación"), 10);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            final String realPath = getRealPathFromURI(uri);
            this.f = new File(realPath);
            this.imageUser.setImageURI(uri);
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String result;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}