package com.congreso.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.congreso.proyectoconia.Database.Entities.patrocinio
import com.congreso.proyectoconia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerpatrociniocurso.view.*

class adapterPatrocinio(var patrocinio : List<patrocinio>) : RecyclerView.Adapter<adapterPatrocinio.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerpatrociniocurso, parent, false))
    }

    override fun getItemCount(): Int = patrocinio.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(patrocinio[position])

    internal fun setPatrocinio(patrocinio : List<patrocinio>){
        this.patrocinio = patrocinio
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(patrocinio : patrocinio){
            view.tv_titulo.text = patrocinio.nombre
            Picasso.get().load(patrocinio.imagen).placeholder(R.drawable.load).into(view.iv_banner)

        }
    }
}