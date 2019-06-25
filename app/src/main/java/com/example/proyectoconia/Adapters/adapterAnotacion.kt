package com.example.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.recycleranotacion.view.*


class adapterAnotacion(var anotacion : List<anotacion>, var clickListener:(anotacion)->Unit, var clickListenerDelete: (anotacion) -> Unit) : RecyclerView.Adapter<adapterAnotacion.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycleranotacion,parent,false))
    }

    override fun getItemCount(): Int = anotacion.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.onBind(anotacion[position],clickListener, clickListenerDelete)

    internal fun setAnotacion(anotacion:List<anotacion>){
        this.anotacion=anotacion
        notifyDataSetChanged()
    }

    class ViewHolder (var view : View) : RecyclerView.ViewHolder(view) {
        fun onBind(anotacion: anotacion, clickListener: (anotacion) -> Unit, clickListenerDelete: (anotacion) -> Unit){
            view.tv_titulo_anotacion.text = anotacion.titulo
            view.tv_ponencia_anotacion.text = anotacion.archivo
            view.tv_hora_fecha_anotacion.text = "Ultima modificación: " + anotacion.fecha

            view.tv_modificar.setOnClickListener {
                clickListener(anotacion)
            }

            view.tv_eliminar.setOnClickListener {
                clickListenerDelete(anotacion)
            }
        }

    }

}