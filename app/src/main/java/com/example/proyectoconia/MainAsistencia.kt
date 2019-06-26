package com.example.proyectoconia

import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.proyectoconia.Activities.SecondMain
import com.example.proyectoconia.Activities.TercerActivity
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.ViewModel.CONIAViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.actionsecond.*
import kotlinx.android.synthetic.main.calificacion_layout.*
import kotlinx.android.synthetic.main.calificacion_layout.view.*

class MainAsistencia : AppCompatActivity(), fragmentAsistencia.OnClickListener {

    var idProgramacion = ArrayList<String>()

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onClickComentarios(programa: programacion) {
        var intent = Intent(this, SecondMain::class.java)
        intent.putExtra(constantes.VENTANA_SECUNDARIA_SIN_USUARIO, 10)
        startActivity(intent)
    }

    override fun onClickCalificacion(programa: programacion) {
        pruebaVentana(programa)
    }

    override fun onClickInfo(programa: programacion) {
        var intent = Intent(this, TercerActivity::class.java)
        intent.putExtra(constantes.VENTANA_PROGRAMA, programa)
        intent.putExtra(constantes.VENTANA_PONENTE_PROGRAMACION, 0)
        startActivity(intent)
    }

    override fun onClickSave() {
        var viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)
        viewModel.updateoinsertAsistenciaApi(user?.email.toString(), idProgramacion, (0).toFloat())
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

    lateinit var viewModel : CONIAViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_asistencia)

        tv_back.setOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProviders.of(this).get(CONIAViewModel::class.java)

        viewModel.getOneAsistenciaUsuario(user?.email.toString()).observe(this, Observer {asistencia ->
            idProgramacion.clear()
            asistencia?.let {uno ->
                uno.forEach {
                    idProgramacion.add(it.fk_programacion_asistencia)
                }
            }
            Log.d("Hola", idProgramacion.toString())
        })

        tv_name_activity.text = "Programa (Inscripción)"

        supportFragmentManager.beginTransaction().replace(R.id.ll_asistencia, fragmentAsistencia()).commit()

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
