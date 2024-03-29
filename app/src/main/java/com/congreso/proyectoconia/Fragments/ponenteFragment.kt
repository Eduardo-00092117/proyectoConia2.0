package com.congreso.proyectoconia.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.congreso.proyectoconia.Adapters.adapterPonente
import com.congreso.proyectoconia.Database.Entities.ponente
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.fragment_ponente.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ponenteFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ponenteFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ponenteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: onClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    interface onClickListener {
        // TODO: Update argument type and name
        fun listenerFunction(ponente : ponente)
    }









    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_ponente, container, false)

        var viewModel = ViewModelProviders.of(activity!!).get(CONIAViewModel::class.java)

        var adapter = adapterPonente(emptyList(), {ponente -> (listener?.listenerFunction(ponente))})

        view.rv_ponente.adapter = adapter
        view.rv_ponente.layoutManager = LinearLayoutManager(context)

        viewModel.getAllPonente().observe(this, Observer { ponente ->
            ponente?.let {adapter.setPonente(it)}
        })

        return view
    }







    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is onClickListener) {
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
         * @return A new instance of fragment ponenteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ponenteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
