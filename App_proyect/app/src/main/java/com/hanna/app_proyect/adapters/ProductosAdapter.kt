package com.hanna.app_proyect.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanna.app_proyect.R
import com.hanna.app_proyect.modelos.Producto

class ProductoAdapter (val productos:ArrayList<Producto>) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>(){
    var position:Int=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoAdapter.ViewHolder {
        var vista = LayoutInflater.from(parent.context).inflate(R.layout.item_producto,parent,false )
        return  ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return  productos.size
    }

    override fun onBindViewHolder(holder: ProductoAdapter.ViewHolder, position: Int) {
        this.position=position
        holder.bindItems(productos[position])
    }
    class  ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                Log.e("POSICION", this.adapterPosition.toString())
                var contexto = itemView.context
                contexto.startActivity( Intent(contexto, VerProducto::class.java) )
            }
        }
        fun bindItems(producto :Producto){
            val itemNombre = itemView.findViewById<TextView>(R.id.item_nombre)
            itemNombre.text =producto.nombre
        }
    }

}