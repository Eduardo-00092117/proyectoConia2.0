package com.congreso.proyectoconia.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.congreso.proyectoconia.Database.Entities.programacion
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragment_info_programa.view.*


class fragment_info_programa : Fragment() {

    var programa : programacion? = null

    companion object {
        fun newInstance(programacion: programacion) : fragment_info_programa {
            var instancia = fragment_info_programa()
            instancia.programa = programacion
            return instancia
        }
    }

    interface OnActionListener {
        fun onClickListener()
    }

    private var listener: OnActionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_fragment_info_programa, container, false)

        view.tv_titulo.text = "Titulo: " + programa?.descripcion
        view.tv_lugar.text = "Lugar: "  + programa?.lugar
        view.tv_fecha.text = "Fecha: "  +programa?.fecha
        view.tv_hora.text = "Hora: "  + programa?.hora_inicio + " - " + programa?.hora_fin

        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

        Log.d("Hola", programa?._id.toString())

        var ponentes = ""

        viewModel.getPonentes(programa?._id.toString()).observe(this, Observer { ponente ->
            ponente?.let { lista ->
                lista.forEach {
                    ponentes += "- " + it.nombre + "\n"
                }
            }
            if (ponentes == ("- ninguno" + "\n")){
                view.tv_ponentes.text = "Información de ponentes no disponible"
            } else{
                view.tv_ponentes.text = ponentes
            }
        })

        if (activity?.mostrarinformacion != null){
            view.tv_cerrar.visibility = View.VISIBLE
            view.tv_cerrar.setOnClickListener {
                listener?.onClickListener()
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnActionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
