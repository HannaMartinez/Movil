package com.bs.recyapp.activity.ui.Empresas;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.recyapp.R;
import com.bs.recyapp.activity.ui.Favoritos.DatosManualidad;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adaptadorRecyclerEmpresa extends RecyclerView.Adapter<adaptadorRecyclerEmpresa.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<DatosManualidad> datos;
    private View.OnClickListener listener;
    Context contexto;

    public adaptadorRecyclerEmpresa(ArrayList<DatosManualidad> datos, Context contexto) {
        this.datos = datos;
        this.contexto = contexto;
    }
    @NonNull
    @Override
    public adaptadorRecyclerEmpresa.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.datos_empresa,null,false);

        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adaptadorRecyclerEmpresa.ViewHolderDatos holder, int position) {
        holder.titulo.setText(datos.get(position).getNombre());
        holder.descripcion.setText(datos.get(position).getAutor());
        holder.ubicacion.setText(datos.get(position).getDescripcion());
        Glide.with(contexto).load("http://192.168.1.69:3000/api/get-imagen-empresa/"+(datos.get(position).getFoto())).into(holder.portada);

    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView titulo,descripcion,ubicacion;
        ImageView portada;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            descripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            ubicacion = (TextView) itemView.findViewById(R.id.tvUbicacion);
            portada = (ImageView) itemView.findViewById(R.id.tvImagen);


        }
    }
}
