package com.example.agendaappws;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    RadioButton RBsesion;
    String e, p;

    private boolean isActivateRadioButton;
    private static final String STRING_PREFERENCES = "";
    private static final String PREFERENCE_ESTADO_BUTTON_SESION = "estado.button.sesion";

    ProgressDialog progressDialog;

    RequestQueue requestQueue;

    String HttpURI = "http://192.168.1.69/agenda/usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ObtenerEstadoButton()) {
            Intent intent1 = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent1);
        }
        //Enlazamos controladores
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLoging);
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

        //Inicializamos a requestQueue y el progressDialog
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        progressDialog = new ProgressDialog(MainActivity.this);

        //Oyentes para el botón Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loging();
            }
        });
        CrearSharedPreferences();
    }
    public void guardarEstadoButton(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,RBsesion.isChecked()).apply();
    }
    public boolean ObtenerEstadoButton() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION,false);
    }
    private void Loging(){
        guardarEstadoButton();
        String e = etEmail.getText().toString();
        String p = etPassword.getText().toString();
        String n = e;

        if (e.isEmpty() || p.isEmpty())
            Toast.makeText(getApplicationContext(), "Debes introducir los dos campos", Toast.LENGTH_LONG).show();
            else{
                //Mostramos el progressDialog
                //progressDialog.setMessage("Procesando...");
                //progressDialog.show();

                SharedPreferences sp1 = getSharedPreferences("Mispreferencias",Context.MODE_PRIVATE);

                String email = sp1.getString("email", "no se encontro");
                String password = sp1.getString("password", "no se encontro");
            if (e.equals(email) && p.equals(password)){
                Toast.makeText(getApplicationContext(), "Bienvenid@ ", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(),PrincipalActivity.class);
                startActivity(intent1);
            }

                //Creacion de la cadena a ejecutar en el web service mediante Volley
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURI,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String serverResponse) {
                                //Ocultamos el progressDialog
                                progressDialog.dismiss();

                                try {
                                    JSONObject obj = new JSONObject(serverResponse);
                                    Boolean error = obj.getBoolean("error");
                                    String mensaje = obj.getString("mensaje");
                                    Intent intent1 = new Intent(getApplicationContext(), PrincipalActivity.class);
                                    intent1.putExtra("nombre", n);
                                    startActivity(intent1);

                                    if (error = true)
                                        Toast.makeText(getApplicationContext(), mensaje,
                                                Toast.LENGTH_LONG).show();
                                    else {
                                        Toast.makeText(getApplicationContext(), "Acceso correcto",
                                                Toast.LENGTH_LONG).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Ocultamos el progressDialog
                                progressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), error.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }) {
                            protected Map<String, String> getParams() {
                                Map<String, String> parametros = new HashMap<>();
                                parametros.put("email", e);
                                parametros.put("password", p);
                                parametros.put("opcion", "login");
                                return parametros;
                            }
                };

                requestQueue.add(stringRequest);
            }
        }
        private void CrearSharedPreferences(){
        SharedPreferences prefer1 = getSharedPreferences("Mispreferencias",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer1.edit();
        editor.putString("email",e);
        editor.putString("password",p);
        editor.commit();
    }
    }