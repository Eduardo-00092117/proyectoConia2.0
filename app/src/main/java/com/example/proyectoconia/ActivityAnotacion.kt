package com.example.proyectoconia

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoconia.Database.Entities.anotacion
import kotlinx.android.synthetic.main.actionsecond.*

class ActivityAnotacion : AppCompatActivity(), fragment_anotacion.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anotacion)

        supportFragmentManager.beginTransaction().replace(R.id.anotacion_linear_layout,fragment_anotacion()).commit()

        tv_name_activity.text="Anotacion"

        tv_back.setOnClickListener {
            onBackPressed()
        }
    }
}
