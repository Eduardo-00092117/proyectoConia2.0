package com.congreso.proyectoconia

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.congreso.proyectoconia.Adapters.adapterComentario
import com.congreso.proyectoconia.Database.Entities.comentario
import com.congreso.proyectoconia.Database.Entities.programacion
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_comentarios.*
import kotlinx.android.synthetic.main.fragment_comentarios.view.*
import kotlinx.android.synthetic.main.fragment_fragment_info_ponente.view.*
import kotlinx.android.synthetic.main.fragment_fragment_info_ponente.view.tv_cerrar


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [comentariosFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [comentariosFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class comentariosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnActionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    interface OnActionListener {
        // TODO: Update argument type and name
        fun onClickListener3()
    }

    var programa : programacion? = null

    companion object {
        fun newInstance(programa : programacion) : comentariosFragment{
            var instance = comentariosFragment()
            instance.programa = programa
            return instance
        }
    }


    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_comentarios, container, false)

        if (activity?.mostrarinformacion != null){
            view.tv_cerrar.visibility = View.VISIBLE
            view.tv_cerrar.setOnClickListener {
                listener?.onClickListener3()
            }
        }

        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

        viewModel.comentarioAsync()


        var adapter = adapterComentario(emptyList())

        view.rv_comentarios.adapter = adapter
        view.rv_comentarios.layoutManager = LinearLayoutManager(context)

        view.tv_titulo.text = programa?.descripcion!! + " - " + user?.email!!

        viewModel.getAllComentario("5d0a9e430d2c5b3f3cbe2a4d").observe(this, Observer { comentario ->
            comentario?.let {
                adapter.setComentario(it)
                rv_comentarios.scrollToPosition(it.size-1)
            }
        })

        view.btn_enviar.setOnClickListener{
            viewModel.insertComentarioApi(user?.email!!, programa?._id!!, view.ed_comentario.text.toString(), "12/12/2020", "12:12 am")
        }

        return view
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnActionListener) {
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
}
