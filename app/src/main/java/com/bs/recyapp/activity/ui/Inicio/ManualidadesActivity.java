package com.bs.recyapp.activity.ui.Inicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bs.recyapp.R;
import com.bs.recyapp.activity.ui.Favoritos.DatosManualidad;
import com.bs.recyapp.activity.ui.Favoritos.adaptadorRecycler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManualidadesActivity extends AppCompatActivity implements Response.Listener <JSONObject>,Response.ErrorListener {

    RecyclerView lista;
    ArrayList<DatosManualidad> datos;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualidades);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        if (b!=null){
            categoria = b.getString("categoria");
        }

        requestQueue = Volley.newRequestQueue(this);
        datos = new ArrayList<>();
        lista = (RecyclerView) findViewById(R.id.Lista);
        lista.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        coneexionApi ();
    }

    private void coneexionApi(){
        String url="http://192.168.1.69:3000/api/consultaManualidadfiltro/"+categoria;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        Toast.makeText(this,error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("datos");
        try {
            for(int i=0;i<json.length();i++){
                DatosManualidad datosM  = new DatosManualidad();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                datosM.setAutor(jsonObject.optString("autor"));
                datosM.setFoto(jsonObject.optString("foto"));
                datosM.setNombre(jsonObject.optString("nombre"));
                datosM.setDescripcion(jsonObject.optString("descripcion"));
                datosM.setValoracion(jsonObject.optString("valoracion"));
                datosM.setId(jsonObject.optString("manualidadN"));
                datos.add(datosM);
            }
            progressDialog.hide();
            adaptadorRecycler adapter = new adaptadorRecycler(datos, this);

            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Pasos = new Intent(ManualidadesActivity.this,PasosManualidadInicio.class);
                    Pasos.putExtra("TIT",datos.get(lista.getChildAdapterPosition(v)).getNombre());
                    Pasos.putExtra("id",datos.get(lista.getChildAdapterPosition(v)).getId());
                    startActivity(Pasos);
                }
            });
            lista.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}