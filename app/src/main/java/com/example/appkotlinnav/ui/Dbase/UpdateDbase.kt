package com.example.appkotlinnav.ui.Dbase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appkotlinnav.DataBaseHandler
import com.example.appkotlinnav.R
import com.example.appkotlinnav.User
import kotlinx.android.synthetic.main.activity_main_data_bases.*
import kotlinx.android.synthetic.main.activity_update_dbase.*

class UpdateDbase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_dbase)

        val context = this

        fun validarEmail(): Boolean{

            var emailvalidacion = etvUpdatedEmail.text.toString().trim()

            var emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            if (emailvalidacion.matches(emailPattern.toRegex()))
            {
                Toast.makeText(getApplicationContext(),"Email valido",Toast.LENGTH_SHORT).show();
                return true
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Email invalido ", Toast.LENGTH_SHORT).show();
                return false
            }

        }

        btn_updatetable.setOnClickListener {


            if (validarEmail() == true &&

                etvSelectId.text.toString().length > 0 &&

                etvUpdatedEmail.text.toString().length > 0 &&

                etvUpdatedUserName.text.toString().length > 0 &&

                etvUpdatedPassword.text.toString().length > 0

                /*etvUpdatedPin.text.toString().length > 0*/) {

                var id = etvSelectId.text.toString().toInt()

                var user = User(etvUpdatedEmail.text.toString(),etvUpdatedUserName.text.toString() ,etvUpdatedPassword.text.toString()/*, etvUpdatedPin.text.toString().toInt()*/)

                var db = DataBaseHandler(context)

                db.updateTableById(user, id)

                val intent = Intent(this, ShowData::class.java)
                startActivity(intent);
            }
            else {
                Toast.makeText(context, "Porfavor llene todos los campos", Toast.LENGTH_SHORT).show()
            }


        }

    }
}