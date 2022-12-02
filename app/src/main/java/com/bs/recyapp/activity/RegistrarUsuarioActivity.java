package com.bs.recyapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bs.recyapp.R;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    private File f;
    //private ClienteViewModel clienteViewModel;
    //private UsuarioViewModel usuarioViewModel;
    //private DocumentoAlmacenadoViewModel documentoAlmacenadoViewModel;
    private Button btnSubirImagen, btnGuardarDatos;
    private CircleImageView imageUser;
    private EditText edtNameUser, edtApellidoPaternoU, edtApellidoMaternoU,
            edtPasswordUser, edtEmailUser;
    private TextInputLayout txtInputNameUser, txtInputApellidoPaternoU, txtInputApellidoMaternoU,
            txtInputEmailUser, txtInputPasswordUser;
    private final static int LOCATION_REQUEST_CODE = 23;

    String nombre,app,apm,password,email;

    ProgressDialog progressDialog;

    RequestQueue requestQueue;
    String HttpURI="https://recycraft-app.herokuapp.com/api/registro";
    //String HttpURI="http://192.168.1.67:3977/api/registro";
    //String HttpURI="http://172.20.10.3:3977/api/registro";
    //String HttpURI="http://recycraft.webcindario.com/usuario.php";
    //String HttpURI="http://192.168.1.70/agenda/usuario.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        this.init();

        requestQueue = Volley.newRequestQueue(RegistrarUsuarioActivity.this);
        progressDialog = new ProgressDialog(RegistrarUsuarioActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnGuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    LOCATION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Gracias por conceder los permisos para " +
                            "leer el almacenamiento, estos permisos son necesarios para poder " +
                            "escoger tu foto de perfil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No podemos realizar el registro si no nos concedes los permisos para leer el almacenamiento.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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


    private boolean validar() {
        boolean retorno = true;

        String nombres, apellidoPaterno, apellidoMaterno, correo, clave;
        nombres = edtNameUser.getText().toString();
        apellidoPaterno = edtApellidoPaternoU.getText().toString();
        apellidoMaterno = edtApellidoMaternoU.getText().toString();

        correo = edtEmailUser.getText().toString();
        clave = edtPasswordUser.getText().toString();
         if (this.f == null) {
          errorMessage("debe selecionar una foto de perfil");
          retorno = false;
        }
        if (nombres.isEmpty()) {
            txtInputNameUser.setError("Ingresar nombres");
            retorno = false;
        } else {
            txtInputNameUser.setErrorEnabled(false);
        }
        if (apellidoPaterno.isEmpty()) {
            txtInputApellidoPaternoU.setError("Ingresar apellido paterno");
            retorno = false;
        } else {
            txtInputApellidoPaternoU.setErrorEnabled(false);
        }
        if (apellidoMaterno.isEmpty()) {
            txtInputApellidoMaternoU.setError("Ingresar apellido materno");
            retorno = false;
        } else {
            txtInputApellidoMaternoU.setErrorEnabled(false);
        }
        if (correo.isEmpty()) {
            txtInputEmailUser.setError("Ingresar correo electrónico");
            retorno = false;
        } else {
            txtInputEmailUser.setErrorEnabled(false);
        }
        if (clave.isEmpty()) {
            txtInputPasswordUser.setError("Ingresar clave para el inicio de sesión");
            retorno = false;
        } else {
            txtInputPasswordUser.setErrorEnabled(false);
        }
        return retorno;
    }
    public void errorMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(message).show();
    }

    private void guardar(){
        nombre = edtNameUser.getText().toString();
        app = edtApellidoPaternoU.getText().toString();
        apm = edtApellidoMaternoU.getText().toString();
        password = edtPasswordUser.getText().toString();
        email = edtEmailUser.getText().toString();
        //foto = imageUser.setImageURI();

        if(nombre.isEmpty() || app.isEmpty() || apm.isEmpty() || password.isEmpty() || email.isEmpty())
            validar();
        else{
            //Mostramos el progressDialog
            progressDialog.setMessage("Procesando...");
            progressDialog.show();

            //Creacion de la cadena a ejecutar en el web Service medinte volley
            StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String serverResponse) {
                            //Ocultamos el progressDialog
                            progressDialog.dismiss();

                            try{
                                JSONObject obj = new JSONObject(serverResponse);
                                Boolean error = obj.getBoolean("error");
                                String mensaje = obj.getString("mensaje");

                                if(error == true)
                                    Toast.makeText(getApplicationContext(), mensaje,
                                            Toast.LENGTH_LONG).show();
                                else{
                                    successMessage("Estupendo " +nombre + " "+ app+ " " + apm +" "+ "tu información ha sido guardada con éxito!");
                                     //(getApplicationContext(),MainActivity.class) /*mensaje,
                                          //   Toast.LENGTH_LONG).show();*/
                                    //finish();
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //ocultamos el progressDialog
                            progressDialog.dismiss();

                            Toast.makeText(getApplicationContext(),error.toString(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }){
                protected Map<String, String> getParams(){
                    Map<String,String> parametros = new HashMap<>();
                    parametros.put("email",email);
                    parametros.put("password",password);
                    parametros.put("nombre",nombre);
                    parametros.put("apellido_p",app);
                    parametros.put("apellido_m",apm);
                   // parametros.put("foto",foto);
                    parametros.put("opcion","registrar");
                    return parametros;
                }
            } ;

            requestQueue.add(stringRequest);
        }
    }
    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                .setContentText(message).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                }).show();
    }
}
