package com.congreso.proyectoconia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.actionsecond.*

class infoAplication : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_aplication)

        tv_back.setOnClickListener {
            onBackPressed()
        }

        tv_name_activity.text = "Información"
    }
}
