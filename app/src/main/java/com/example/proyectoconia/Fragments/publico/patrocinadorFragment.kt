package com.example.proyectoconia.Fragments.publico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoconia.Adapters.adapterPatrocinio
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.fragment_patrocinador.view.*

class patrocinadorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_patrocinador, container, false)

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterPatrocinio(emptyList())

        view.rv_patrocinio.adapter = adapter
        view.rv_patrocinio.layoutManager = LinearLayoutManager(context)

        viewModel.getAllPatrocinio().observe(this, Observer { patro ->
            patro?.let { adapter.setPatrocinio(it) }
        })

        return view
    }

}
