package com.bs.recyapp.activity.ui.Empresas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bs.recyapp.R;
import com.bs.recyapp.activity.ui.Favoritos.DatosManualidad;
import com.bs.recyapp.activity.ui.Favoritos.PasosManualidad;
import com.bs.recyapp.activity.ui.Favoritos.adaptadorRecycler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EmpresasFragment extends Fragment implements  Response.Listener <JSONObject>,Response.ErrorListener {
    private View pantalla;
    RecyclerView lista;
    ArrayList<DatosManualidad> datos;

    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    String HttpURI="http://192.168.1.69:3000/api/consultaManualidad";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pantalla =  inflater.inflate(R.layout.fragment_empresa,container,false);

        requestQueue = Volley.newRequestQueue(getContext());
        datos = new ArrayList<>();
        lista = (RecyclerView) pantalla.findViewById(R.id.ListaEmpresa);
        lista.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        coneexionApi ();
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
                datosM.setId(jsonObject.optString("manualidadN"));
                datos.add(datosM);
            }
            progressDialog.hide();
            adaptadorRecycler adapter = new adaptadorRecycler(datos, pantalla.getContext());

            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent PasosManualidad = new Intent(pantalla.getContext(), com.bs.recyapp.activity.ui.Favoritos.PasosManualidad.class);
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