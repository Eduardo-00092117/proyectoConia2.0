package com.congreso.proyectoconia.Fragments

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
import com.congreso.proyectoconia.Database.Entities.anotacion
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.congreso.proyectoconia.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.actionsecond.*
import kotlinx.android.synthetic.main.fragment_fragment_anotacion.*
import kotlinx.android.synthetic.main.fragment_fragment_anotacion.view.*
import java.text.SimpleDateFormat
import java.util.*


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

    companion object {
        fun newInstance(anotacion: anotacion) : fragment_anotacion {
            var intent = fragment_anotacion()
            intent.anotacion = anotacion
            return intent
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

        adapterProgramacion.add("Seleccione una opción")

        viewModel.getAllProgramacion2().observe(this, Observer {
                progra ->
            progra.let{
                it.forEach {
                        dato-> adapterProgramacion.add(dato.descripcion)
                }
                view.sp_programacion.adapter = adapterProgramacion
            }
        })

        if (activity?.tv_name_activity != null){
            if(anotacion==null){
                activity?.tv_name_activity?.text="Agregar anotación"
            } else{
                activity?.tv_name_activity?.text="Modificar anotación"
            }
        }

        if(anotacion!=null){
            view.btn_guardarAnotacion.text = "Modificar"

            view.titulo_anotacion.setText(anotacion?.titulo)

            view.et_archivo.setText(anotacion?.archivo)

            viewModel.getOneProgramacion2(anotacion?.fk_programacion_anotacion.toString()).observe(this, Observer { progra ->
                progra?.let {
                    var posicion = adapterProgramacion.getPosition(it.descripcion)
                    sp_programacion.setSelection(posicion)
                }
            })
        }
        view.btn_guardarAnotacion.setOnClickListener {
            var titulo = view.titulo_anotacion.text.toString()
            //var spUser = sp_usuario.selectedItem.toString()
            var spProgra = sp_programacion.selectedItem.toString()
            var archivo = view.et_archivo.text.toString()

            var date = SimpleDateFormat("dd/MM/yyyy - hh:mm a")
            val currentTime = date.format(Date())

            if (titulo != "" && archivo != "" && sp_programacion.selectedItemPosition != 0){
                if (anotacion == null){
                    viewModel.setAnotacionApi(titulo,currentTime,archivo, user?.email.toString(),spProgra)

                    Toast.makeText(this.context, "Anotacion ingresada", Toast.LENGTH_LONG).show()
                } else{
                    viewModel.updateAnotacionApi(anotacion?._id.toString(), titulo,currentTime,archivo, user?.email.toString(),spProgra)

                    Toast.makeText(this.context, "Anotacion modificada", Toast.LENGTH_LONG).show()
                }

                activity?.onBackPressed()
            } else{
                Toast.makeText(this.context, "Debe llenar todos los campos!", Toast.LENGTH_LONG).show()
            }
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
}
