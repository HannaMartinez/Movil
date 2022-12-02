package com.bs.recyapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bs.recyapp.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    Button btnAcceder;
    private TextView txtNuevoUsuario;
    private EditText etUsuario, etPassword;
    private TextInputLayout email, password;
    String e,p;

    ProgressDialog progressDialog;

    RequestQueue requestQueue;

    //String HttpURI="http://192.168.1.67/agenda/usuario.php";
   //String HttpURI="http://recycraft.webcindario.com/usuario.php";
   //String HttpURI="http://172.20.10.3:3977/api/login";
   //String HttpURI="http://192.168.1.67:3977/api/login";
    String HttpURI="https://recycraft-app.herokuapp.com/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazar los controladores con la vista
        btnAcceder = (Button) findViewById(R.id.btnAcceder);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
        txtNuevoUsuario = (TextView) findViewById(R.id.txtNuevoUsuario);
        email = (TextInputLayout) findViewById(R.id.txtInputEmailUser);
        password =(TextInputLayout) findViewById(R.id.txtInputPasswordUser);

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        progressDialog = new ProgressDialog(MainActivity.this);

       btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                Login();
            }
        });
       txtNuevoUsuario.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              Intent i = new Intent(getApplicationContext(), RegistrarUsuarioActivity.class);
              startActivity(i);
              overridePendingTransition(R.anim.left_in,R.anim.left_out);
           }
       });
    }
    private void Login() {
        boolean retorno = true;
        e = etUsuario.getText().toString();
        p = etPassword.getText().toString();

        if(e.isEmpty() || p.isEmpty()) {
            toastDatos("");
            //Toast.makeText(getApplicationContext(),"Debes llenar los campos",
            //Toast.LENGTH_LONG).show();
        }
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

                                if(error == true) {
                                    //toastIncorrecto1("mensaje");
                                    /*Toast.makeText(getApplicationContext(), mensaje,
                                            Toast.LENGTH_SHORT).show();
                                */}else{
                                    toastCorrecto("");
                                    //Toast.makeText(getApplicationContext(), "Acceso correcto",
                                      //      Toast.LENGTH_LONG).show();

                                    Intent intent1 = new Intent(getApplicationContext(),InicioActivity.class);
                                    startActivity(intent1);
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
                            toastIncorrecto1("");
                            //Toast.makeText(getApplicationContext(),error.toString(),
                                    //Toast.LENGTH_SHORT).show();
                        }
                    }){
                protected Map<String, String> getParams(){
                    Map<String,String> parametros = new HashMap<>();
                    parametros.put("email",e);
                    parametros.put("password",p);
                    parametros.put("opcion","login");
                    return parametros;
                }
            } ;

            requestQueue.add(stringRequest);
        }

    }
    public void  toastCorrecto(String msg){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_ok,
                (ViewGroup) findViewById(R.id.ll_custom_toast_ok));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM,0,200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
    public void  toastIncorrecto(String msg){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error,
                (ViewGroup) findViewById(R.id.ll_custom_toast_error));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM,0,200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
    public void  toastDatos(String msg){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_datos,
                (ViewGroup) findViewById(R.id.ll_custom_toast_datos));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM,0,200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
    public int toastIncorrecto1(String mensaje){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error_login,
                (ViewGroup) findViewById(R.id.ll_custom_toast_error_login));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM,0,200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
        return 0;
    }
    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("has oprimido el botón atrás")
                .setContentText("¿Quieres cerrar la aplicación?")
                .setCancelText("No, Cancelar!").setConfirmText("Sí, Cerrar")
                .showCancelButton(true).setCancelClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Operación cancelada")
                            .setContentText("No saliste de la app")
                            .show();
                }).setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismissWithAnimation();
                    System.exit(0);
                }).show();
    }
    }