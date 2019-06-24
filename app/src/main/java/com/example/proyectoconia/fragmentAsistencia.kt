package com.example.proyectoconia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoconia.Adapters.adapterAsistencia
import com.example.proyectoconia.Adapters.adapterPrograma
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_fragment_asistencia.*
import kotlinx.android.synthetic.main.fragment_fragment_asistencia.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [fragmentAsistencia.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [fragmentAsistencia.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class fragmentAsistencia : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    interface OnClickListener {
        // TODO: Update argument type and name
        fun onClickAddListener(programa : programacion)
        fun onClickDeleteListener(programa : programacion)
        fun onClickSave()
        fun onClickInfo(programa: programacion)
    }



    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_fragment_asistencia, container, false)

        view.tabs.addTab(view.tabs.newTab().setText("Dia 1"))
        view.tabs.addTab(view.tabs.newTab().setText("Dia 2"))

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterAsistencia(emptyList(), {programa -> listener?.onClickAddListener(programa)}, {programa -> listener?.onClickDeleteListener(programa)}, {programa -> listener?.onClickInfo(programa)}, emptyList())

        view.rv_programacion.adapter = adapter
        view.rv_programacion.layoutManager = LinearLayoutManager(context)

        //Segundo recyclerView
        var adapter2 = adapterAsistencia(emptyList(), {programa -> listener?.onClickAddListener(programa)}, {programa -> listener?.onClickDeleteListener(programa)}, {programa -> listener?.onClickInfo(programa)}, emptyList())

        view.rv_programacion2.adapter = adapter2
        view.rv_programacion2.layoutManager = LinearLayoutManager(context)


        view.tv_guardar.setOnClickListener {
            listener?.onClickSave()
        }

        Log.d("Hola", user?.email.toString())

        viewModel.getProgramaAsistencia(user?.email.toString()).observe(this, Observer {asistencia ->
            asistencia?.let { progra ->
                viewModel.getAllProgramacion("1").observe(this, Observer { ponente ->
                    ponente?.let {adapter.setProgramacion(it, progra)}
                })
                viewModel.getAllProgramacion("2").observe(this, Observer { ponente ->
                    ponente?.let {adapter2.setProgramacion(it, progra)}
                })
            }
        })



        view.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0 -> {
                        rv_programacion.visibility = View.VISIBLE
                        rv_programacion2.visibility = View.GONE
                    }
                    1 -> {
                        rv_programacion.visibility = View.GONE
                        rv_programacion2.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        return view
    }











    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragmentAsistencia.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragmentAsistencia().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
