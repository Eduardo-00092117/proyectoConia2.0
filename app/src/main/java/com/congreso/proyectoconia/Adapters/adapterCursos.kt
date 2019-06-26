package com.congreso.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.congreso.proyectoconia.Database.Entities.curso
import com.congreso.proyectoconia.R
import com.squareup.picasso.Picasso
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

            Picasso.get().load(curso.imagen)
                    .placeholder(R.drawable.load)
                    .into(view.iv_banner)

        }
    }
}