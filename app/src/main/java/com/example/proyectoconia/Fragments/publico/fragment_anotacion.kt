package com.example.proyectoconia.Fragments.publico

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_fragment_anotacion.*
import kotlinx.android.synthetic.main.fragment_fragment_anotacion.view.*
import java.util.Calendar





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [fragment_anotacion.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [fragment_anotacion.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class fragment_anotacion : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var anotacion : anotacion?=null
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_fragment_anotacion, container, false)

        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)


        var adapterProgramacion = ArrayAdapter<String>(context,android.R.layout.simple_list_item_1)


        viewModel.getAllProgramacion("1").observe(this, Observer {
                progra ->
            progra.let{
                it.forEach {
                        dato-> adapterProgramacion.add(dato.descripcion)
                }
                view.sp_programacion.adapter = adapterProgramacion
            }
        })

        if(anotacion!=null){
            view.tv_change.text = "Update"

            view.titulo_anotacion.setText(anotacion?.titulo)

            view.et_archivo.setText(anotacion?.archivo)
        }
        view.btn_guardarAnotacion.setOnClickListener {
            var titulo = view.titulo_anotacion.text.toString()
            //var spUser = sp_usuario.selectedItem.toString()
            var spProgra = sp_programacion.selectedItem.toString()
            var archivo = view.et_archivo.text.toString()
            val currentTime = Calendar.getInstance().time.toString()

            user?.email?.let { it1 -> viewModel.setAnotacionApi(titulo,currentTime,archivo, it1,spProgra) }

            Toast.makeText(this.context, "Anotacion ingresada", Toast.LENGTH_LONG).show()
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_anotacion.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_anotacion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
