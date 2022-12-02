package com.bs.recyapp.activity.ui.Favoritos;
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
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adaptadorRecycler extends RecyclerView.Adapter<adaptadorRecycler.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<DatosManualidad> datos;
    private View.OnClickListener listener;
    Context contexto;

    public adaptadorRecycler(ArrayList<DatosManualidad> datos,Context contexto) {
        this.datos = datos;
        this.contexto = contexto;
    }
    @NonNull
    @Override
    public adaptadorRecycler.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.datos_empresa,null,false);

        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adaptadorRecycler.ViewHolderDatos holder, int position) {
        holder.titulo.setText(datos.get(position).getNombre());
        holder.director.setText(datos.get(position).getAutor());
        holder.duracion.setText(datos.get(position).getDescripcion());
        Glide.with(contexto).load("http://192.168.1.69:3000/api/get-imagen-manualidad/"+(datos.get(position).getFoto())).into(holder.portada);
        holder.favoritos.setProgress(Integer.valueOf(datos.get(position).getValoracion()));
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
        TextView titulo,director,duracion;
        RatingBar favoritos;
        ImageView portada;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            director = (TextView) itemView.findViewById(R.id.tvDirector);
            duracion = (TextView) itemView.findViewById(R.id.tvDuracion);
            portada = (ImageView) itemView.findViewById(R.id.tvImagen);
            favoritos = (RatingBar) itemView.findViewById(R.id.rating);

        }
    }
}
