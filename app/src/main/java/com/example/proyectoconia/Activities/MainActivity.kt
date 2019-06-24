package com.example.proyectoconia.Activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.proyectoconia.Database.Entities.asistencia
import com.example.proyectoconia.Database.Entities.ponente
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.Fragments.publico.*
import com.example.proyectoconia.R
import com.example.proyectoconia.constantes
import com.example.proyectoconia.Fragments.publico.fragment_info_ponente
import com.example.proyectoconia.Fragments.publico.fragment_info_programa
import com.example.proyectoconia.MainAsistencia
import com.example.proyectoconia.fragmentAsistencia
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_inicio.*

class MainActivity : AppCompatActivity(), inicioFragment.OnFragmentInteractionListener, programaFragment.OnActionListener, ponenteFragment.onClickListener,
        fragment_info_programa.OnActionListener, fragment_info_ponente.OnActionListener, fragmentAsistencia.OnClickListener {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var idProgramacion = ArrayList<String>()

    override fun onClickInfo(programa: programacion) {
        var fragment = fragment_info_programa.newInstance(programa)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragment).commit()
    }

    override fun onClickSave() {
        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)
        viewModel.updateoinsertAsistenciaApi(user?.email.toString(), idProgramacion.toString().replace("[", "").replace("]", ""), (4.5).toFloat())
    }

    override fun onClickDeleteListener(programa: programacion) {
        idProgramacion.remove(programa._id)
        Log.d("Hola", idProgramacion.toString().replace("[", "").replace("]", ""))
    }

    override fun onClickAddListener(programa: programacion) {
        idProgramacion.add(programa._id)
        Log.d("Hola", idProgramacion.toString().replace("[", "").replace("]", ""))
    }

    override fun onClickListener2() {
        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, ponenteFragment()). commit()
    }

    override fun listenerFunction(ponente: ponente) {
        var fragment = fragment_info_ponente.newInstance(ponente)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragment).commit()
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
        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

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
                    if (user?.email == null){
                        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, programaFragment()). commit()
                    } else{
                        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragmentAsistencia()). commit()
                    }
                }
                7 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, contactoFragment()). commit()

            }
        } else {
            if (fragment != 0) {
                if (user?.email != null && fragment == 6){
                    startActivity(Intent(this, MainAsistencia::class.java))
                } else{
                    var intent = Intent(this, SecondMain::class.java)
                    intent.putExtra(constantes.VENTANA_SECUNDARIA_SIN_USUARIO, fragment)
                    startActivity(intent)
                }
            }
        }


    }
}
