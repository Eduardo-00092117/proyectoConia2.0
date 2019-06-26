package com.congreso.proyectoconia.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.congreso.proyectoconia.Database.Entities.*
import com.congreso.proyectoconia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragment_info_ponente.view.*


class fragment_info_ponente : Fragment() {

    var ponente : ponente? = null

    companion object {
        fun newInstance(ponente: ponente) : fragment_info_ponente {
            var instancia = fragment_info_ponente()
            instancia.ponente = ponente
            return instancia
        }
    }

    interface OnActionListener {
        fun onClickListener2()
    }

    private var listener: OnActionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_fragment_info_ponente, container, false)

        view.tv_nombre.text = ponente?.nombre

        Picasso.get().load(ponente?.foto)
                .placeholder(R.drawable.load)
                .into(view.iv_foto_ponente)

        view.tv_descripcion.text = ponente?.descripcion

        if (activity?.mostrarinformacion != null){
            view.tv_cerrar.visibility = View.VISIBLE
            view.tv_cerrar.setOnClickListener {
                listener?.onClickListener2()
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
