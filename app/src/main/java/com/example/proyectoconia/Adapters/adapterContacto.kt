package com.example.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectoconia.Database.Entities.contacto
import com.example.proyectoconia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclercontacto.view.*

class adapterContacto(var contacto : List<contacto>) : RecyclerView.Adapter<adapterContacto.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclercontacto, parent, false))
    }

    override fun getItemCount(): Int = contacto.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(contacto[position])

    internal fun setContacto(contacto : List<contacto>){
        this.contacto = contacto
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(contacto : contacto){
            view.tv_titulo.text = contacto.titulo
            view.tv_descripcion.text = contacto.descripcion

            Picasso.get().load(contacto.imagen)
                    .placeholder(R.drawable.load)
                    .into(view.iv_icono)

        }
    }
}