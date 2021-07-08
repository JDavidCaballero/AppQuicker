package com.example.appkotlinnav.ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.appkotlinnav.MainActivity
import com.example.appkotlinnav.R
import com.example.appkotlinnav.ui.Dbase.MainActivityDataBases
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_register
import kotlinx.android.synthetic.main.activity_login.imageButtonarrow
import kotlinx.android.synthetic.main.activity_main_data_bases.*
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {

    private lateinit var etvUser: EditText
    private lateinit var etvPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        imageButtonarrow.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        etvUser = findViewById<EditText>(R.id.etvUser)
        etvPassword = findViewById<EditText>(R.id.etvPassword)

        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        auth = FirebaseAuth.getInstance()

        

        btn_register.setOnClickListener {

            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    fun forgotPassword(view: View) {

        startActivity(Intent(this, Forgotpassword::class.java))
    }

    fun login(view: View) {

        loginUser()

    }

    private fun loginUser() {

        val user: String = etvUser.text.toString()
        val password: String = etvPassword.text.toString()

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {

            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this) {

                    task ->

                if (task.isSuccessful) {

                    Toast.makeText(this,"Sesión iniciada corractamente", Toast.LENGTH_SHORT).show()
                    action()
                }else{

                    Toast.makeText(this, "Correo o contraseña no validos, verifique sus credenciales", Toast.LENGTH_SHORT).show()

                }


            }


        }
    }

    private fun action(){

    val intent = Intent(this, MainActivityDataBases::class.java)
    startActivity(intent)
    }

}