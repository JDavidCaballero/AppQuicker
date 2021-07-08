package com.example.appkotlinnav.ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.appkotlinnav.R
import com.google.firebase.auth.FirebaseAuth

class Forgotpassword : AppCompatActivity() {

    private lateinit var etvCorreo: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)

        etvCorreo = findViewById<EditText>(R.id.etvCorreo)

        progressBar = findViewById<ProgressBar>(R.id.progressBar)


        auth = FirebaseAuth.getInstance()
    }

    fun send(view:View){

        val email = etvCorreo.text.toString()

        if(!TextUtils.isEmpty(email)){

            progressBar.visibility = View.VISIBLE

            auth.sendPasswordResetEmail(email).addOnCompleteListener(this){

                task ->

                if(task.isSuccessful){

                    Toast.makeText(this, "Email enviado", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, Login::class.java))


                }else{

                    Toast.makeText(this, "Error al enviar email, verifique el correo", Toast.LENGTH_SHORT).show()

                }



            }


        }

    }



}