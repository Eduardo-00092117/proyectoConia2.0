package com.example.proyectoconia.Fragments.publico

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoconia.Activities.ActivityAnotacion
import com.example.proyectoconia.Adapters.adapterAnotacion
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_main2.view.*
import kotlinx.android.synthetic.main.fragment_anotacion.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [anotacionFragment.OnClickListener] interface
 * to handle interaction events.
 * Use the [anotacionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class anotacionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnClickListener? = null

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    interface OnClickListener {
        // TODO: Update argument type and name
        fun onClickListener(anotacion: anotacion)
        fun onClickListenerDelete(anotacion: anotacion)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_anotacion, container, false)

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterAnotacion(emptyList(),{anotacion->(listener?.onClickListener(anotacion))}, {anotacion->(listener?.onClickListenerDelete(anotacion))})

        view.fab_agregar.setOnClickListener {
            startActivity(Intent(this.context, ActivityAnotacion::class.java))
        }

        if(user?.email!=null){
            Log.d("quepedo","EN EL IFFFF")

            view.rv_anotacion.adapter = adapter
            view.rv_anotacion.layoutManager = LinearLayoutManager(context)

            viewModel.getAllAnotacion(user?.email.toString()).observe(this, Observer {
                anotacion->anotacion?.let { adapter.setAnotacion(it) }
            })


        } else{
            Log.d("holi","Nothing to show")
        }

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
         * @return A new instance of fragment anotacionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            anotacionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
