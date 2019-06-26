package com.congreso.proyectoconia.Fragments.publico

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.congreso.proyectoconia.Database.ViewModel.CONIAViewModel
import com.congreso.proyectoconia.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.fragment_fragment_registro.*
import kotlinx.android.synthetic.main.fragment_fragment_registro.view.*
import android.widget.AdapterView as AdapterView1


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [fragmentRegistro.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [fragmentRegistro.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class fragmentRegistro : Fragment() {
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





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_fragment_registro, container, false)

        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

        var adapterGenero = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1)
        var adapterPais = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1)
        var adapterCarrera = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1)
        var adapterNivel = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1)
        var adapterTipo = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1)

        viewModel.getAllGenero().observe(this, Observer { genero ->
            adapterGenero.clear()
            adapterGenero.add("Seleccione una opción")
            genero?.let {
                it.forEach {dato ->
                    adapterGenero.add(dato.nombre)
                }
                view.sp_genero.adapter = adapterGenero
            }
        })

        viewModel.getAllPais().observe(this, Observer { genero ->
            adapterPais.clear()
            adapterPais.add("Seleccione una opción")
            genero?.let {
                it.forEach {dato ->
                    adapterPais.add(dato.nombre)
                }
                view.sp_pais.adapter = adapterPais
            }
        })

        viewModel.getAllCarrera().observe(this, Observer { genero ->
            adapterCarrera.clear()
            adapterCarrera.add("Seleccione una opción")
            genero?.let {
                it.forEach {dato ->
                    if (dato.nombre != "Ninguna"){
                        adapterCarrera.add(dato.nombre)
                    }
                }
                view.sp_carrera.adapter = adapterCarrera
            }
        })

        viewModel.getAllNivel().observe(this, Observer { genero ->
            adapterNivel.clear()
            adapterNivel.add("Seleccione una opción")
            genero?.let {
                it.forEach {dato ->
                    if (dato.nombre != "Ninguno"){
                        adapterNivel.add(dato.nombre)
                    }
                }
                view.sp_nivel.adapter = adapterNivel
            }
        })

        viewModel.getAllTipo().observe(this, Observer { genero ->
            adapterTipo.clear()
            adapterTipo.add("Seleccione una opción")
            genero?.let {
                it.forEach {dato ->
                    adapterTipo.add(dato.nombre)
                }
                view.sp_tipo.adapter = adapterTipo
            }
        })

        view.sp_tipo?.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {

            }

            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                var visible = View.VISIBLE
                var invisible = View.GONE
                if (position == 0){
                    limpiarCampos()
                    formulario(invisible, invisible, invisible, invisible, invisible, invisible, invisible, invisible, invisible, invisible)
                }else if (position == 1){
                    limpiarCampos()
                    formulario(visible, visible, visible, visible, visible, invisible, invisible, invisible, visible, visible)
                } else if(position == 2){
                    limpiarCampos()
                    formulario(visible, visible, visible, visible, visible, visible, visible, visible, invisible, invisible)
                }
            }
        }

        view.btn_registrarse.setOnClickListener {
            var tipo = sp_tipo.selectedItem.toString()

            var nombre = view.et_nombre.text.toString()
            var apellido = view.et_apellido.text.toString()
            var correo = view.et_correo.text.toString()
            var pass = view.ed_pass.text.toString()
            var pass2 = view.ed_pass2.text.toString()

            var genero = sp_genero.selectedItem.toString()
            var pais = sp_pais.selectedItem.toString()

            var carrera = sp_carrera.selectedItem.toString()
            var nivel = sp_nivel.selectedItem.toString()

            if (carrera == "Seleccione una opción" && nivel == "Seleccione una opción" && sp_tipo.selectedItemPosition == 1){
                nivel = "Ninguno"
                carrera = "Ninguna"
            }

            var empresa = view.et_empresa.text.toString()
            var formacion = view.et_formacion.text.toString()
            var instituto = view.et_instituto.text.toString()


            if (nombre != "" && apellido != "" && correo != "" && pass != "" && pass2 != "" && genero != "Seleccione una opción" &&
                    pais != "Seleccione una opción"){

                var bandera = 0
                if(sp_tipo.selectedItemPosition == 2){
                    if (carrera == "Seleccione una opción" || nivel == "Seleccione una opción"){
                        bandera = 1
                    }
                } else if (sp_tipo.selectedItemPosition == 1){
                    if (formacion == "" || instituto == ""){
                        bandera = 1
                    }
                }

                if (bandera == 0){
                    if (validarCorreo(correo)){
                        if (pass == pass2){
                            if (pass.length >= 6){

                                viewModel.setUsuarioApi(nombre, apellido, pass, correo, genero, pais, carrera, nivel, empresa, formacion, instituto, tipo)

                                registrar(correo,pass2)

                                Toast.makeText(context, "Registro completado", Toast.LENGTH_LONG).show()

                                activity!!.onBackPressed()

                            } else{
                                Toast.makeText(context, "La contraseña debe contener un minimo de 6 caracteres!", Toast.LENGTH_LONG).show()
                            }
                        } else{
                            Toast.makeText(context, "Las contraseñas deben coincidir", Toast.LENGTH_LONG).show()
                        }
                    } else{
                        Toast.makeText(context, "El formato del correo no es valido!", Toast.LENGTH_LONG).show()
                    }
                } else{
                    Toast.makeText(context, "Debe completar todos los campos obligatorios!", Toast.LENGTH_LONG).show()
                }

            } else{
                Toast.makeText(context, "Debe completar todos los campos obligatorios!", Toast.LENGTH_LONG).show()
            }

        }

        return view
    }

    fun validarCorreo(correo : String) : Boolean{
        var pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(correo).matches()
    }

    fun formulario(nombre : Int, apellido : Int, correo : Int, genero : Int, pais : Int, carrera : Int, nivel : Int, empresa : Int,
                   formacion : Int, institucion : Int){
        et_nombre.visibility = nombre
        et_apellido.visibility = apellido
        et_correo.visibility = correo
        sp_genero.visibility = genero
        sp_pais.visibility = pais
        sp_carrera.visibility = carrera
        sp_nivel.visibility = nivel

        et_empresa.visibility = empresa

        et_formacion.visibility = formacion
        et_instituto.visibility = institucion

        tv_nombre.visibility = nombre
        tv_apellido.visibility = apellido
        tv_correo.visibility = correo

        tv_genero.visibility = genero
        tv_pais.visibility = pais
        tv_carrera.visibility = carrera
        tv_nivel.visibility = nivel

        tv_empresa.visibility = empresa
        tv_formacion.visibility = formacion
        tv_instituto.visibility = institucion

        ll_pass.visibility = nombre

        btn_registrarse.visibility = nombre
    }

    fun limpiarCampos(){
        et_nombre.setText("")
        et_apellido.setText("")
        et_correo.setText("")
        sp_genero.setSelection(0)
        sp_pais.setSelection(0)
        sp_carrera.setSelection(0)
        sp_nivel.setSelection(0)

        et_empresa.setText("")

        et_formacion.setText("")
        et_instituto.setText("")

        ed_pass.setText("")
        ed_pass2.setText("")
    }







    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
    private fun registrar(email:String,pass:String) {

        if (email.isEmpty() || pass.isEmpty()) {
            Log.d("mains", "HEY PONE DATOS VO")
            return
        }
        Log.d("mains", "email es: " + email)
        Log.d("mains", "pass es: $pass")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                }

                Log.d("mains", "SE CREO, su id es: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("mains", "NO SE CREO: ${it.message}")
            }
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("mains-correo", "CORREO ENVIADO")
                } else {
                    Log.d("mains-correo", "CORREO NO ENVIADO")
                }
            }
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
         * @return A new instance of fragment fragmentRegistro.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragmentRegistro().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
