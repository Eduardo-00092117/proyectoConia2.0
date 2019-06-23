package com.example.proyectoconia.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoconia.Database.Entities.ponente
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Fragments.publico.*
import com.example.proyectoconia.R
import com.example.proyectoconia.constantes
import com.example.proyectoconia.Fragments.publico.fragment_info_ponente
import com.example.proyectoconia.Fragments.publico.fragment_info_programa
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_inicio.*

class MainActivity : AppCompatActivity(), inicioFragment.OnFragmentInteractionListener, programaFragment.OnActionListener, ponenteFragment.onClickListener,
        fragment_info_programa.OnActionListener, fragment_info_ponente.OnActionListener {
    override fun onClickListener2() {
        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, ponenteFragment()). commit()
    }

    override fun listenerFunction(ponente: ponente) {
        var fragment = fragment_info_ponente.newInstance(ponente)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragment).commit()
    }

    override fun onClickListener() {
        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, programaFragment()).commit()
    }

    override fun onClickListener(programa: programacion) {
        var fragment = fragment_info_programa.newInstance(programa)

        supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, fragment).commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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
                6 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, programaFragment()). commit()
                7 -> supportFragmentManager.beginTransaction().replace(R.id.mostrarinformacion, contactoFragment()). commit()

            }
        } else {
            if (fragment != 0) {
                var intent = Intent(this, SecondMain::class.java)
                intent.putExtra(constantes.VENTANA_SECUNDARIA_SIN_USUARIO, fragment)
                startActivity(intent)
            }
        }


    }
}
