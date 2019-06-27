package com.congreso.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.congreso.proyectoconia.Database.Entities.comentario
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.recyclercomentarios.view.*

class adapterComentario(var comentario : List<comentario>, var viewModel : CONIAViewModel, var context : LifecycleOwner) : RecyclerView.Adapter<adapterComentario.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclercomentarios, parent, false))
    }

    override fun getItemCount(): Int = comentario.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(comentario[position], viewModel, context)

    internal fun setComentario(comentario : List<comentario>){
        this.comentario = comentario
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(comentario : comentario, viewModel : CONIAViewModel, context : LifecycleOwner){

            viewModel.getUsuarioAsistencia(comentario.fk_asistencia_comentario).observe(context, Observer {
                view.tv_usuario.text = it.correo
                view.tv_comentario.text = comentario.comentario
                view.tv_hora.text = comentario.fecha + " - " + comentario.hora
            })

        }
    }
}