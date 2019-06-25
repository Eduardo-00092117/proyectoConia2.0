package com.example.proyectoconia.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.Entities.ponente
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.example.proyectoconia.Fragments.publico.*
import com.example.proyectoconia.R
import com.example.proyectoconia.Fragments.publico.anotacionFragment
import com.example.proyectoconia.constantes
import kotlinx.android.synthetic.main.actionsecond.*
import kotlinx.android.synthetic.main.activity_second_main.*


class SecondMain : AppCompatActivity(), ponenteFragment.onClickListener, programaFragment.OnActionListener, SwipeRefreshLayout.OnRefreshListener, anotacionFragment.OnClickListener{
    private lateinit var viewModel : CONIAViewModel
    private var getIntent : Int = 0

    override fun onClickListenerDelete(anotacion: anotacion) {
        viewModel.deleteAnotacionApi(anotacion._id)
    }

    override fun onClickListener(anotacion: anotacion) {
        var intent = Intent(this, ActivityAnotacion::class.java)
        intent.putExtra(constantes.UNA_ANOTACION, anotacion)
        startActivity(intent)
    }

    override fun listenerFunction(ponente: ponente) {
        var intent = Intent(this, TercerActivity::class.java)
        intent.putExtra(constantes.VENTANA_PROGRAMA, ponente)
        intent.putExtra(constantes.VENTANA_PONENTE_PROGRAMACION, 1)
        startActivity(intent)
    }

    override fun onClickListener(programa: programacion) {
        var intent = Intent(this, TercerActivity::class.java)
        intent.putExtra(constantes.VENTANA_PROGRAMA, programa)
        intent.putExtra(constantes.VENTANA_PONENTE_PROGRAMACION, 0)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_main)

        tv_back.setOnClickListener {
            onBackPressed()
        }

        getIntent = intent.getIntExtra(constantes.VENTANA_SECUNDARIA_SIN_USUARIO, 0)

        var nombre = ""
        when(getIntent){
            1 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal, InfoFragment()).commit()
                nombre = "Información"
            }
            2 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal, fechasFragment()).commit()
                nombre = "Fechas importantes"
            }
            3 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal, patrocinadorFragment()).commit()
                nombre = "Patrocinadores"
            }
            4 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal, cursoFragment()).commit()
                nombre = "Cursos a impartir"
            }
            5 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal, ponenteFragment()).commit()
                nombre = "Ponencias"
            }
            6 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal, programaFragment()).commit()
                nombre = "Programa"
            }
            7 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal, contactoFragment()).commit()
                nombre = "Contactanos"
            }
            8 -> {
                supportFragmentManager.beginTransaction().replace(R.id.principal,
                    anotacionFragment()
                ).commit()
                nombre = "Anotacion"
            }
        }
        tv_name_activity.text = nombre

        viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

        refresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        Handler().postDelayed(Runnable {
            // Remove widget from screen.
            when(getIntent){
                1 -> viewModel.sincronizarInformacion()
                2 -> viewModel.sincronizarFecha()
                3 -> viewModel.sincronizarPatrocinio()
                4 -> viewModel.sincronizarCurso()
                5 -> viewModel.sincronizarPonente()
                6 -> {
                    viewModel.sincronizarProgramacion()
                }
                7 -> viewModel.sincronizarContacto()
                8 -> viewModel.sincronizarAnotacion()
            }
            refresh.isRefreshing = false
        }, 3000)
    }
}
