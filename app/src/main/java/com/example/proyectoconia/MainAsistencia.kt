package com.example.proyectoconia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.proyectoconia.Activities.TercerActivity
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.actionsecond.*

class MainAsistencia : AppCompatActivity(), fragmentAsistencia.OnClickListener {
    var idProgramacion = ArrayList<String>()

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onClickInfo(programa: programacion) {
        var intent = Intent(this, TercerActivity::class.java)
        intent.putExtra(constantes.VENTANA_PROGRAMA, programa)
        intent.putExtra(constantes.VENTANA_PONENTE_PROGRAMACION, 0)
        startActivity(intent)
    }

    override fun onClickSave() {
        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)
        viewModel.updateoinsertAsistenciaApi(user?.email.toString(), idProgramacion.toString().replace("[", "").replace("]", ""), (4.5).toFloat())
        Toast.makeText(this, "Se guardo su selección!", Toast.LENGTH_LONG).show()
    }

    override fun onClickDeleteListener(programa: programacion) {
        idProgramacion.remove(programa._id)
        Log.d("Hola", idProgramacion.toString().replace("[", "").replace("]", ""))
    }

    override fun onClickAddListener(programa: programacion) {
        idProgramacion.add(programa._id)
        Log.d("Hola", idProgramacion.toString().replace("[", "").replace("]", ""))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_asistencia)

        tv_back.setOnClickListener {
            onBackPressed()
        }

        tv_name_activity.text = "Programa (Inscripción)"

        supportFragmentManager.beginTransaction().replace(R.id.ll_asistencia, fragmentAsistencia()).commit()

    }
}
