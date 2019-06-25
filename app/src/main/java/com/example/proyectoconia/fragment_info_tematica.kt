package com.example.proyectoconia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.proyectoconia.Database.Entities.tematica
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragment_info_tematica.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fragment_info_tematica : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var tematica : tematica?=null

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction_info_tematica()
    }

    companion object {
        fun newInstance(tematica: tematica):fragment_info_tematica{
            var instancia = fragment_info_tematica()
            instancia.tematica=tematica
            return instancia
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_fragment_info_tematica, container, false)

        view.tv_nombre_tematica.text = tematica?.titulo

        Log.d("tematicax","Esta es: ${tematica?.titulo}")

        Glide.with(view.context)
            .load(tematica?.imagen)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.iv_foto_tematica)


        view.tv_descripcion_tematica.text=tematica?.descripcion


        if (activity?.mostrarinformacion != null){
            view.tv_cerrar.visibility = View.VISIBLE
            view.tv_cerrar.setOnClickListener {
                listener?.onFragmentInteraction_info_tematica()
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
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
