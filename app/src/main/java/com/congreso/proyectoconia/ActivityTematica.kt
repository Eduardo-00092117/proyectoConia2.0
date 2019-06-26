package com.congreso.proyectoconia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.congreso.proyectoconia.Database.Entities.tematica
import kotlinx.android.synthetic.main.actionsecond.*

class ActivityTematica : AppCompatActivity(),fragment_info_tematica.OnFragmentInteractionListener {
    override fun onFragmentInteraction_info_tematica() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tematica)

        tv_name_activity.text = "Tematica"

        var intent =intent.getParcelableExtra<tematica>(constantes.UNA_TEMATICA)

        var fragment = fragment_info_tematica.newInstance(intent)

        supportFragmentManager.beginTransaction().replace(R.id.ll_tematica,fragment).commit()

        tv_back.setOnClickListener {
            onBackPressed()
        }
    }
}
