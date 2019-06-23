package com.example.proyectoconia.Fragments.publico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoconia.Adapters.adapterContacto
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.fragment_contacto.view.*

class contactoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_contacto, container, false)

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterContacto(emptyList())

        view.rv_contacto.adapter = adapter
        view.rv_contacto.layoutManager = LinearLayoutManager(context)

        viewModel.getAllContacto().observe(this, Observer { contacto ->
            contacto?.let { adapter.setContacto(it) }
        })

        return view
    }
}
