package com.example.proyectoconia.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoconia.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.actionsecond.*
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_name_activity.text = "Iniciar Sesión"

        btn_iniciar_sesion.setOnClickListener {
            val email = et_email_login.text.toString()
            val pass = et_pass_login.text.toString()

            Log.d("login", "correo: $email")

            signIn(email, pass)
        }
        tv_back.setOnClickListener {
            onBackPressed()
        }

        btn_link_registrar.setOnClickListener {
            //registrar()
            startActivity(Intent(this, ActivityRegistro::class.java))
        }
        btn_forget_pass.setOnClickListener {
            startActivity(Intent(this, forgetPass::class.java))
        }
    }

    fun signIn(email: String, pass: String) {
        if (email.trim() != "" && pass.trim() != "") {
            auth.signInWithEmailAndPassword(email.replace(" ", ""), pass)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Log.d("loginx", "HEY: ${auth.currentUser?.email}")
                        var intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("id", auth.currentUser?.email)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Contraseña /usuario incorrecto", Toast.LENGTH_LONG).show()
                    }
                })
        }else{
            Toast.makeText(this, "Ingrese correo y contrasena, porfavor", Toast.LENGTH_LONG).show()

        }
    }
}

