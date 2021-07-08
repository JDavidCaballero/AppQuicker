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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_register
import kotlinx.android.synthetic.main.activity_main_data_bases.*
import kotlinx.android.synthetic.main.activity_main_data_bases.imageButtonarrow
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private lateinit var etvNombre:EditText
    private lateinit var etvCorreo:EditText
    private lateinit var etvContraseña:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etvNombre = findViewById<EditText>(R.id.etvNombre)
        etvCorreo = findViewById<EditText>(R.id.etvCorreo)
        etvContraseña = findViewById<EditText>(R.id.etvContraseña)

        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")

        imageButtonarrow.setOnClickListener{

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

    fun validarRegistroDePin(): Boolean {

        var passwordconteiner : String = etvContraseña.text.toString().trim()

        var passwordconteinerconfirmm : String = etvContraseñaconfirm.text.toString().trim()

        if(passwordconteiner.equals(passwordconteinerconfirmm) &&  etvNombre.text.toString().length > 0 &&

            etvCorreo.text.toString().length > 0 && etvContraseña.text.toString().length > 0 ){

            Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show()

            return true

        }else{

            Toast.makeText(this, "Rellene todos los campos "+"\n"+"Debe ingresar la misma contraseña en los dos campos", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    fun register(view: View){
        
        val validarContraseña = validarPassword()
        val validarRegistro = validarRegistroDePin()


        if (validarContraseña == true) {
            if (validarRegistro == true) {
                createNewAccount()
            }
        }

    }

    fun validarPassword(): Boolean{

      val largoContraseña = etvContraseña.text.toString().length

        if( largoContraseña < 6){

            Toast.makeText(this, "La contraseña debe tener mas de seis caracteres", Toast.LENGTH_SHORT).show()
            return false
        }else  {

            Toast.makeText(this, "La contraseña fue digitada correctamente", Toast.LENGTH_SHORT).show()

            return true

        }

    }

    private fun createNewAccount(){

        val name:String = etvNombre.text.toString()
        val email:String = etvCorreo.text.toString()
        val password:String = etvContraseña.text.toString()

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) ){

            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){

                task ->

                if(task.isComplete){

                    val user : FirebaseUser = task.result!!.user!!

                    verifyEmail(user)


                    val userdb = dbReference.child(user.uid)

                    userdb.child("Name").setValue(name)

                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)

                }
            }
        }

    }

    private fun verifyEmail(user:FirebaseUser){

        user.sendEmailVerification().addOnCompleteListener(this){

            task ->

            if(task.isComplete){

                Toast.makeText(this, "Email enviado", Toast.LENGTH_SHORT).show()
            }else{

                Toast.makeText(this, "Error, no se pudo enviar Email", Toast.LENGTH_SHORT).show()
            }

        }

    }


}