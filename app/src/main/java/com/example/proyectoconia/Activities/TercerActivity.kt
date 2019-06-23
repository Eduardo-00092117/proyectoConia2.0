package com.example.proyectoconia.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoconia.Database.Entities.ponente
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.R
import com.example.proyectoconia.constantes
import com.example.proyectoconia.Fragments.publico.fragment_info_ponente
import com.example.proyectoconia.Fragments.publico.fragment_info_programa
import kotlinx.android.synthetic.main.actionsecond.*

class TercerActivity : AppCompatActivity(), fragment_info_programa.OnActionListener, fragment_info_ponente.OnActionListener {
    override fun onClickListener2() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercer)

        var ventana = intent.getIntExtra(constantes.VENTANA_PONENTE_PROGRAMACION, 0)

        if (ventana == 0){
            var intent = intent.getParcelableExtra<programacion>(constantes.VENTANA_PROGRAMA)

            tv_name_activity.text = "Detalle"

            tv_back.setOnClickListener {
                onBackPressed()
            }

            var fragment = fragment_info_programa.newInstance(intent)

            supportFragmentManager.beginTransaction().replace(R.id.ll_tercero, fragment).commit()
        } else if (ventana == 1){
            var intent = intent.getParcelableExtra<ponente>(constantes.VENTANA_PROGRAMA)

            tv_name_activity.text = "Detalle"

            tv_back.setOnClickListener {
                onBackPressed()
            }

            var fragment = fragment_info_ponente.newInstance(intent)

            supportFragmentManager.beginTransaction().replace(R.id.ll_tercero, fragment).commit()
        }
    }
}
