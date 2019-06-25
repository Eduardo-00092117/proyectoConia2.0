package com.example.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.Entities.tematica
import com.example.proyectoconia.Fragments.publico.ponenteFragment
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.recyclertematica.view.*

class adapterTematica(var tematica: List<tematica>, var clickListener: (tematica)->Unit):RecyclerView.Adapter<adapterTematica.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclertematica,parent,false))
    }

    override fun getItemCount(): Int = tematica.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(tematica[position], clickListener)

    internal fun setTematica(tematica: List<tematica>){
        this.tematica=tematica
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View):RecyclerView.ViewHolder(view){
        fun onBind(tematica: tematica,clickListener: (tematica) -> Unit){

            view.tv_nombre_tematica.text = tematica.titulo

            Glide.with(itemView.context)
                .load(tematica.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view.iv_foto_tematica)

            view.setOnClickListener {
                clickListener(tematica)
            }
        }

    }

}