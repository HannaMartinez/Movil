package com.bs.recyapp.activity.ui.Favoritos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bs.recyapp.R;
import com.bs.recyapp.activity.InicioActivity;
import com.bs.recyapp.activity.MainActivity;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavoritosFragment extends Fragment implements Response.Listener <JSONObject>,Response.ErrorListener{
    private View pantalla;
    RecyclerView lista;
    ArrayList<DatosManualidad> datos;

    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    //String HttpURI="http://172.20.10.3:3977/api/consultaManualidad";
    //String HttpURI="https://recycraft-app.herokuapp.com/api/consultaManualidad";
    String HttpURI="http://192.168.1.69:3000/api/consultaManualidad";
    //String HttpURI="http://192.168.1.67/agenda/usuario.php";

    String imagen = "http://192.168.137.228/agenda/agua.png";
    String imagen2 = "http://192.168.137.228/agenda/isc_logo.png";
    String[] datosImg;// = {imagen,imagen2};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pantalla = inflater.inflate(R.layout.fragment_favoritos,container,false);
        //AppCompatActivity activity = (AppCompatActivity) getActivity();
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*requestQueue = Volley.newRequestQueue(pantalla.getContext());
        progressDialog = new ProgressDialog(pantalla.getContext());*/
        requestQueue = Volley.newRequestQueue(getContext());
        datos = new ArrayList<>();
        lista = (RecyclerView) pantalla.findViewById(R.id.Lista);
        lista.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        coneexionApi ();
       // lista.setAdapter(new adaptador(pantalla.getContext(),datos,datosImg));
       /* lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent PasosManualidad = new Intent(view.getContext(), PasosManualidad.class);
                PasosManualidad.putExtra("TIT",datos[position][0]);
                PasosManualidad.putExtra("PASOS",datos[position][4]);
                startActivity(PasosManualidad);
            }
        });*/
        return  pantalla;
    }
    private void coneexionApi() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,HttpURI,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
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
        adaptadorRecycler adapter = new adaptadorRecycler(datos, pantalla.getContext());

            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent PasosManualidad = new Intent(pantalla.getContext(), PasosManualidad.class);
                    PasosManualidad.putExtra("TIT",datos.get(lista.getChildAdapterPosition(v)).getNombre());
                    PasosManualidad.putExtra("id",datos.get(lista.getChildAdapterPosition(v)).getId());
                    startActivity(PasosManualidad);
                }
            });
        lista.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}