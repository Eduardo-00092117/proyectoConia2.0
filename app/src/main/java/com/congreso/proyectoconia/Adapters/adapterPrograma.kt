package com.congreso.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.congreso.proyectoconia.Database.Entities.programacion
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.recyclerprograma.view.*

class adapterPrograma(var programacion : List<programacion>, var clicklistener : (programacion) -> Unit) : RecyclerView.Adapter<adapterPrograma.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerprograma, parent, false))
    }

    override fun getItemCount(): Int = programacion.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(programacion[position], clicklistener)

    internal fun setProgramacion(programacion : List<programacion>){
        this.programacion = programacion
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(programacion : programacion, clicklistener : (programacion) -> Unit){

            view.tv_titulo.text = programacion.descripcion
            view.tv_lugar.text = "Lugar: "+programacion.lugar
            view.tv_fecha.text = " " +programacion.fecha
            view.tv_hora.text = " " + programacion.hora_inicio + " - " + programacion.hora_fin

            view.tv_mas.setOnClickListener { clicklistener(programacion) }

        }
    }
}