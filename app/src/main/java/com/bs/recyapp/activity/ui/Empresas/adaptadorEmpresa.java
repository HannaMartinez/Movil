package com.bs.recyapp.activity.ui.Empresas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.recyapp.R;
import com.bs.recyapp.activity.ui.Favoritos.DatosPaso;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adaptadorEmpresa extends RecyclerView.Adapter<adaptadorEmpresa.ViewHolderDatos> {
    ArrayList<DatosPaso> datos;
    Context contexto;

    public adaptadorEmpresa(ArrayList<DatosPaso> datos, Context contexto) {
        this.datos = datos;
        this.contexto = contexto;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_sitioweb,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.descripcion.setText(datos.get(position).getDescripcion());
        Glide.with(contexto).load("http://192.168.1.69:3000/api//get-imagen-empresa/"+(datos.get(position).getFoto())).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView descripcion;
        ImageView imagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            descripcion = (TextView) itemView.findViewById(R.id.txtPasos);
            imagen = (ImageView) itemView.findViewById(R.id.tvPasos);
        }
    }
}
