package com.congreso.proyectoconia.Activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.congreso.proyectoconia.Fragments.publico.fragmentRegistro
import com.congreso.proyectoconia.R
import kotlinx.android.synthetic.main.actionsecond.*

class ActivityRegistro : AppCompatActivity(), fragmentRegistro.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        supportFragmentManager.beginTransaction().replace(R.id.registro, fragmentRegistro()).commit()

        tv_name_activity.text = "Registro"

        tv_back.setOnClickListener {
            onBackPressed()
        }


    }
}
