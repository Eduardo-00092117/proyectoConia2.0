package com.example.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoconia.Database.Entities.informacion
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.recyclerinformacion.view.*

class adapterInformacion(var informacion : List<informacion>) : RecyclerView.Adapter<adapterInformacion.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerinformacion, parent, false))
    }

    override fun getItemCount(): Int = informacion.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(informacion[position])

    internal fun setInformacion(informacion : List<informacion>){
        this.informacion = informacion
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(informacion : informacion){
            view.tv_titulo.text = informacion.titulo

            view.tv_descripcion.text = informacion.descripcion

        }
    }
}