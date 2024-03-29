package com.congreso.proyectoconia.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.congreso.proyectoconia.Activities.MainActivity
import com.congreso.proyectoconia.R
import com.congreso.proyectoconia.Activities.loginActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_inicio.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [inicioFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [inicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class inicioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    lateinit var viewModel: CONIAViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater.inflate(R.layout.fragment_inicio, container, false)

        viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

        view.tv_usuario.text = "Usuario: " + user?.email

        viewModel.getAllGaleria().observe(this, Observer { genero ->
            genero?.let {
                if (it.size > 0) {
                    Picasso.get()
                        .load(it[0].imagen)
                        .placeholder(R.drawable.load)
                        .into(view.app_bar_image_viewer)
                }
            }
        })
        if (user?.email != null) {
            view.tv_iniciar.visibility = View.GONE
            view.btn_anotacion.visibility = View.VISIBLE
            view.tv_salir.visibility = View.VISIBLE
        } else {

            view.tv_iniciar.text = "Iniciar Sesion"

        }

        view.tv_salir.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, MainActivity::class.java))
        }

        view.tv_iniciar.setOnClickListener {
            startActivity(Intent(context, loginActivity::class.java))
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
         * @return A new instance of fragment inicioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            inicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
