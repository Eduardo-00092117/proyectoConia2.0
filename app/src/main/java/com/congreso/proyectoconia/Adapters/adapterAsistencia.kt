package com.congreso.proyectoconia.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.congreso.proyectoconia.Database.Entities.programacion
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.recyclerasistencia.view.*

class adapterAsistencia(var programacion : List<programacion>, var clicklistener : (programacion) -> Unit, var clicklistener2 : (programacion) -> Unit,
                        var clicklistener3 : (programacion) -> Unit, var programacion2: List<programacion>, var calificacionListener : (programacion) -> Unit,
                        var comentariosListener : (programacion) -> Unit) : RecyclerView.Adapter<adapterAsistencia.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerasistencia, parent, false))
    }

    override fun getItemCount(): Int = programacion.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(programacion[position], clicklistener, clicklistener2, clicklistener3, programacion2, calificacionListener, comentariosListener)

    internal fun setProgramacion(programacion: List<programacion>, programacion2: List<programacion>) {
        this.programacion = programacion
        this.programacion2 = programacion2
        notifyDataSetChanged()
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(programacion: programacion, clicklistener: (programacion) -> Unit, clicklistener2: (programacion) -> Unit,
                   clicklistener3 : (programacion) -> Unit, programacion2: List<programacion>, calificacionListener : (programacion) -> Unit,
                   comentariosListener : (programacion) -> Unit) {

            view.tv_titulo.text = programacion.descripcion
            view.tv_lugar.text = "Lugar: " + programacion.lugar
            view.tv_fecha.text = " " + programacion.fecha
            view.tv_hora.text = " " + programacion.hora_inicio + " - " + programacion.hora_fin

            view.tv_mas.setOnClickListener { clicklistener3(programacion) }


            view.cb_asistencia.setOnClickListener {
                if (view.cb_asistencia.isChecked){
                    clicklistener(programacion)
                } else{
                    clicklistener2(programacion)
                }
            }

            programacion2.forEach {
                if (programacion._id == it._id){
                    view.cb_asistencia.isChecked = true
                    view.ll_botones.visibility = View.VISIBLE
                }
            }

            view.btn_calificacion.setOnClickListener{
                calificacionListener(programacion)
            }

            view.btn_comentar.setOnClickListener{
                comentariosListener(programacion)
            }

        }
    }
}
