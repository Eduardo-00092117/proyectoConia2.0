package com.congreso.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.congreso.proyectoconia.Database.Entities.fechaImportante
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.recyclerfechas.view.*

class adapterFechas(var fechas : List<fechaImportante>) : RecyclerView.Adapter<adapterFechas.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerfechas, parent, false))
    }

    override fun getItemCount(): Int = fechas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(fechas[position])

    internal fun setFechas(fechas : List<fechaImportante>){
        this.fechas = fechas
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(fechas : fechaImportante){
            view.tv_titulo.text = fechas.titulo
            view.tv_fecha.text = " " + fechas.fecha
        }
    }
}