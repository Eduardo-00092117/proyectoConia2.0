package com.example.proyectoconia.Activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.proyectoconia.*
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.Entities.ponente
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.Entities.tematica
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.Fragments.publico.*
import com.example.proyectoconia.Fragments.publico.fragment_info_ponente
import com.example.proyectoconia.Fragments.publico.fragment_info_programa
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calificacion_layout.*
import kotlinx.android.synthetic.main.fragment_inicio.*

class MainActivity : AppCompatActivity(), inicioFragment.OnFragmentInteractionListener, programaFragment.OnActionListener, ponenteFragment.onClickListener,
        fragment_info_programa.OnActionListener, fragment_info_ponente.OnActionListener, fragmentAsistencia.OnClickListener,
    fragment_anotacion.OnFragmentInteractionListener, anotacionFragment.OnClickListener,tematicaFragment.OnFragmentInteractionListener,fragment_info_tematica.OnFragmentInteractionListener,
    comentariosFragment.OnActionListener{
    override fun onClickListener3() {
        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragmentAsistencia()). commit()
    }

    override fun onClickComentarios(programa: programacion) {
        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, comentariosFragment()). commit()
    }

    override fun onClickCalificacion(programa: programacion) {
        pruebaVentana(programa)
    }

    override fun onClickListener2() {
        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, ponenteFragment()). commit()
    }

    override fun listenerFunction(ponente: ponente) {
        var fragment = fragment_info_ponente.newInstance(ponente)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragment).commit()
    }

    override fun onFragmentInteraction_info_tematica() {
        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion,tematicaFragment()).commit()
    }

    override fun onFragment_tematica_Interaction(tematica: tematica) {
        var fragment = fragment_info_tematica.newInstance(tematica)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion,fragment).commit()
    }

    lateinit var viewModel : CONIAViewModel

    override fun onClickListenerDelete(anotacion: anotacion) {
        viewModel.deleteAnotacionApi(anotacion._id)
    }

    override fun onClickListener(anotacion: anotacion) {
        var intent = Intent(this, ActivityAnotacion::class.java)
        intent.putExtra(constantes.UNA_ANOTACION, anotacion)
        startActivity(intent)
    }

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var idProgramacion = ArrayList<String>()

    override fun onClickInfo(programa: programacion) {
        var fragment = fragment_info_programa.newInstance(programa)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragment).commit()
    }

    override fun onClickSave() {
        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)
        viewModel.updateoinsertAsistenciaApi(user?.email.toString(), idProgramacion, (0).toFloat())
        Toast.makeText(this, "Se guardo su selección!", Toast.LENGTH_LONG).show()
    }

    override fun onClickDeleteListener(programa: programacion) {
        idProgramacion.remove(programa._id)
    }

    override fun onClickAddListener(programa: programacion) {
        idProgramacion.add(programa._id)
    }


    override fun onClickListener() {
        if (user?.email == null){
            supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, programaFragment()).commit()
        } else{
            supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragmentAsistencia()).commit()
        }
    }

    override fun onClickListener(programa: programacion) {
        var fragment = fragment_info_programa.newInstance(programa)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragment).commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

        var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected){
            viewModel.sincronizarInformacion()

            viewModel.sincronizarGenero()

            viewModel.sincronizarPais()

            viewModel.sincronizarCarrera()

            viewModel.sincronizarNivel()

            viewModel.sincronizarTipo()

            viewModel.sincronizarUsuario()

            viewModel.sincronizarFecha()

            viewModel.sincronizarPatrocinio()

            viewModel.sincronizarCurso()

            viewModel.sincronizarGaleria()

            viewModel.sincronizarContacto()

            viewModel.sincronizarPonente()

            viewModel.sincronizarProgramacion()

            viewModel.sincronizarAsistencia()

            viewModel.sincronizarAnotacion()

            viewModel.sincronizarTematica()
        } else{
            Toast.makeText(this, "No hay conexion a internet!!!", Toast.LENGTH_LONG).show()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.principal, inicioFragment()).commit()

        if(mostrarinformacion != null){
            supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, InfoFragment()). commit()
        }

    }

    fun click(view: View) {
        var fragment = 0
        when (view) {
            tv_info -> fragment = 1
            tv_fechas -> fragment = 2
            tv_patro -> fragment = 3
            tv_cursos -> fragment = 4
            tv_ponen -> fragment = 5
            tv_progra -> fragment = 6
            tv_contact -> fragment = 7
            btn_anotacion->fragment = 8
            eje_tematico -> fragment = 9
            tv_iniciar -> startActivity(Intent(this, loginActivity::class.java))
        }

        if (mostrarinformacion != null) {
            when(fragment){
                1-> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, InfoFragment()). commit()
                2 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fechasFragment()). commit()
                3 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, patrocinadorFragment()). commit()
                4 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, cursoFragment()). commit()
                5 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, ponenteFragment()). commit()
                6 -> {
                    idProgramacion.clear()
                    if (user?.email == null){
                        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, programaFragment()). commit()
                    } else{
                        viewModel.getOneAsistenciaUsuario(user?.email.toString()).observe(this, Observer {asistencia ->
                            idProgramacion.clear()
                            asistencia?.let {uno ->
                                uno.forEach {
                                    idProgramacion.add(it.fk_programacion_asistencia)
                                }
                            }
                            Log.d("Hola", idProgramacion.toString())
                        })
                        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragmentAsistencia()). commit()
                    }
                }
                7 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, contactoFragment()). commit()
                8 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion,
                    anotacionFragment()
                ).commit()
                9-> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion,tematicaFragment()).commit()
            }
        } else {
            if (fragment != 0) {
                if (user?.email != null && fragment == 6) {
                    startActivity(Intent(this, MainAsistencia::class.java))
                } else {
                    var intent = Intent(this, SecondMain::class.java)
                    intent.putExtra(constantes.VENTANA_SECUNDARIA_SIN_USUARIO, fragment)
                    startActivity(intent)
                }
            }
        }
    }

    lateinit var dialog : Dialog

    fun pruebaVentana(programa: programacion){
        dialog = Dialog(this)
        dialog.setContentView(R.layout.calificacion_layout)
        dialog.setTitle("Dispositivos Bluetooth")
        viewModel.getOneAsistenciaUsuario(user?.email.toString()).observe(this, Observer {asistencia ->
            asistencia?.let {individual ->
                individual.forEach {
                    if (it.fk_programacion_asistencia == programa._id){
                        when(it.calificacion.toInt()){
                            1 -> dialog.star1.isChecked = true
                            2 -> dialog.star2.isChecked = true
                            3 -> dialog.star3.isChecked = true
                            4 -> dialog.star4.isChecked = true
                            5 -> dialog.star5.isChecked = true
                        }
                    }
                }
            }
        })

        dialog.calificar.setOnClickListener {
            var grupo = dialog.findViewById<RadioGroup>(R.id.contentStar)

            var calificacion = 0.0

            when(grupo.checkedRadioButtonId){
                R.id.star1 -> calificacion = dialog.star1.text.toString().toDouble()
                R.id.star2 -> calificacion = dialog.star2.text.toString().toDouble()
                R.id.star3 -> calificacion = dialog.star3.text.toString().toDouble()
                R.id.star4 -> calificacion = dialog.star4.text.toString().toDouble()
                R.id.star5 -> calificacion = dialog.star5.text.toString().toDouble()
            }

            viewModel.updateAsistenciaCalifiacion(user?.email.toString(), programa._id  , calificacion.toFloat())

            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.show()
    }
}
