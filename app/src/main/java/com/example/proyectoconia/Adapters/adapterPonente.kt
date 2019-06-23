package com.example.proyectoconia.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectoconia.Database.Entities.ponente
import com.example.proyectoconia.Fragments.publico.ponenteFragment
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.recyclerponente.view.*

class adapterPonente(var ponente : List<ponente>, var clickListener: (ponente) -> Unit) : RecyclerView.Adapter<adapterPonente.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerponente, parent, false))
    }

    override fun getItemCount(): Int = ponente.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(ponente[position], clickListener)

    internal fun setPonente(ponente : List<ponente>){
        this.ponente = ponente
        notifyDataSetChanged()
    }

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun onBind(ponente : ponente, clickListener: (ponente) -> Unit){

            view.tv_nombre_ponente.text = ponente.nombre

            Glide.with(itemView.context)
                    .load(ponente.foto)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(view.iv_foto_ponente)

            view.setOnClickListener {
                clickListener(ponente)
            }

        }
    }
}