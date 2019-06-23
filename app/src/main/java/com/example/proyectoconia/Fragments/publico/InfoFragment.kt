package com.example.proyectoconia.Fragments.publico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoconia.Adapters.adapterInformacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.fragment_info.view.*

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_info, container, false)

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterInformacion(emptyList())

        view.rv_informacion.adapter = adapter
        view.rv_informacion.layoutManager = LinearLayoutManager(context)

        viewModel.getAllInformacion().observe(this, Observer { info ->
            info?.let { adapter.setInformacion(it) }
        })

        return view
    }

}
