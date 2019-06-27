package com.congreso.proyectoconia.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.congreso.proyectoconia.Database.Entities.anotacion
import com.congreso.proyectoconia.R
import com.congreso.proyectoconia.Fragments.fragment_anotacion
import com.congreso.proyectoconia.Constantes.constantes
import kotlinx.android.synthetic.main.actionsecond.*

class ActivityAnotacion : AppCompatActivity(),
    fragment_anotacion.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anotacion)

        var intent = intent.getParcelableExtra<anotacion>(constantes.UNA_ANOTACION)

        var fragment = fragment_anotacion()

        if (intent != null){
            fragment = fragment_anotacion.newInstance(intent)
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.anotacion_linear_layout,
            fragment
        ).commit()

        tv_back.setOnClickListener {
            onBackPressed()
        }
    }
}
