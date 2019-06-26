package com.congreso.proyectoconia.Fragments.publico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.congreso.proyectoconia.Adapters.adapterFechas
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.fragment_fechas.view.*


class fechasFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fechas, container, false)

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterFechas(emptyList())

        view.rv_fechas.adapter = adapter
        view.rv_fechas.layoutManager = LinearLayoutManager(context)

        viewModel.getAllFechas().observe(this, Observer { fechas ->
            fechas?.let { adapter.setFechas(it) }
        })

        return view
    }

}
