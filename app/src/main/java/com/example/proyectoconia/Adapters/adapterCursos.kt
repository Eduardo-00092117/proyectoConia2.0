package com.example.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectoconia.Database.Entities.curso
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.recyclerpatrociniocurso.view.*

class adapterCursos(var curso : List<curso>) : RecyclerView.Adapter<adapterCursos.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerpatrociniocurso, parent, false))
    }

    override fun getItemCount(): Int = curso.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(curso[position])

    internal fun setCurso(curso : List<curso>){
        this.curso = curso
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(curso : curso){

            view.tv_titulo.text = curso.titulo

            Glide.with(itemView.context)
                    .load(curso.imagen)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(view.iv_banner)

        }
    }
}