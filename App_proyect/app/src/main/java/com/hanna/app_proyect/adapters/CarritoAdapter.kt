package com.hanna.app_proyect.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanna.app_proyect.R
import com.hanna.app_proyect.modelos.ItemCarito

class CarritoAdapter (val productos:ArrayList<ItemCarito>) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>(){
    var position:Int=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoAdapter.ViewHolder {
        var vista = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito,parent,false )
        return  ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return  productos.size
    }

    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {

        }
        fun bindItems(producto : ItemCarito){
            val itemNombre = itemView.findViewById<TextView>(R.id.itemNombreCarrito)
            itemNombre.text =producto.nombre
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.position=position
        holder.bindItems(productos[position])
    }

}