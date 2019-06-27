package com.congreso.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.congreso.proyectoconia.Database.Entities.comentario
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.fragment_comentarios.view.*
import kotlinx.android.synthetic.main.recyclercomentarios.view.*

class adapterComentario(var comentario : List<comentario>) : RecyclerView.Adapter<adapterComentario.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclercomentarios, parent, false))
    }

    override fun getItemCount(): Int = comentario.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(comentario[position])

    internal fun setComentario(comentario : List<comentario>){
        this.comentario = comentario
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(comentario : comentario){

            view.tv_usuario.text = comentario.fk_asistencia_comentario
            view.tv_comentario.text = comentario.comentario
            view.tv_hora.text = comentario.fecha + " - " + comentario.hora

        }
    }
}