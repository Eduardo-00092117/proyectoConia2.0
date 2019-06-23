package com.example.proyectoconia.Fragments.publico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoconia.Adapters.adapterCursos
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.R
import kotlinx.android.synthetic.main.fragment_curso.view.*

class cursoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_curso, container, false)

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterCursos(emptyList())

        view.rv_cursos.adapter = adapter
        view.rv_cursos.layoutManager = LinearLayoutManager(context)

        viewModel.getAllCurso().observe(this, Observer { curso ->
            curso?.let { adapter.setCurso(it) }
        })

        return view
    }
}
