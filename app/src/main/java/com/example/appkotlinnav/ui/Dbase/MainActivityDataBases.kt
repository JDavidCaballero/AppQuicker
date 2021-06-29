package com.example.appkotlinnav.ui.Dbase

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.appkotlinnav.*
import kotlinx.android.synthetic.main.activity_main_data_bases.*
import kotlinx.android.synthetic.main.activity_update_dbase.*

class MainActivityDataBases : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_data_bases)

        val context = this

        fun validarEmail(): Boolean{

            var emailvalidacion = etvEmail.text.toString().trim()

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


        btn_insert.setOnClickListener {


            if (validarEmail() == true && etvEmail.text.toString().length > 0 &&

                etvUserName.text.toString().length > 0 &&

                etvPassword.text.toString().length > 0 ) {

                var user = User(etvEmail.text.toString() ,etvUserName.text.toString(), etvPassword.text.toString()/*, etvPin.text.toString().toInt()*/)
                var db = DataBaseHandler(context)
                db.insertData(user)

                val intent = Intent(this, ShowData::class.java)
                startActivity(intent);
            }
                else {
                Toast.makeText(context, "Porfavor llene todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }


        }

        buttonshowdb.setOnClickListener(){

            val intent = Intent(this, ShowData::class.java)
            startActivity(intent);
        }

        buttonUpdatedb.setOnClickListener(){

            val intent = Intent(this, UpdateDbase::class.java)
            startActivity(intent);
        }


    }
}