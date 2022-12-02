package com.bs.recyapp.activity.ui.Favoritos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bs.recyapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PasosManualidad extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    ArrayList<DatosPaso> datos;
    RecyclerView recycler;
    ProgressDialog progres;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String manualidadid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasos_manualidad_inicio);

        TextView titulo = (TextView) findViewById(R.id.tvTituloDescripcion);
        ImageView imag = (ImageView) findViewById(R.id.imgPortada);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        if (b!=null){
            manualidadid = b.getString("id");
            titulo.setText(b.getString("TIT"));
        }
        request = Volley.newRequestQueue(this);
        datos = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.RVPasos);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        coneexionApi ();
    }

    private void coneexionApi() {
        //String url="http://172.20.10.3:3977/api/consultaPaso/"+manualidadid;
        //String url="https://recycraft-app.herokuapp.com/api/consultaPaso"+manualidadid;
        String url="http://192.168.1.69:3000/api/consultaPaso/"+manualidadid;

        progres = new ProgressDialog(this);
        progres.setMessage("Cargando...");
        progres.show();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progres.hide();
        Toast.makeText(this,error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("datos");
        try {
            for(int i=0;i<json.length();i++){
                DatosPaso datosM  = new DatosPaso();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                datosM.setFoto(jsonObject.optString("foto"));
                datosM.setDescripcion(jsonObject.optString("descripcion"));
                datosM.setManualidad(jsonObject.optString("manualidad"));
                datos.add(datosM);
            }
            progres.hide();
            adaptadorPaso adapter = new adaptadorPaso(datos, this);
            recycler.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}