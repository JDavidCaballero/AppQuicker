package com.example.appkotlinnav.ui.Dbase

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.appkotlinnav.*
import kotlinx.android.synthetic.main.activity_main_data_bases.*
import kotlinx.android.synthetic.main.activity_main_data_bases.imageButtonarrow
import kotlinx.android.synthetic.main.activity_show_data.*
import kotlinx.android.synthetic.main.activity_update_dbase.*

class MainActivityDataBases : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_data_bases)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.Azul)
        }


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

                etvTitulo.text.toString().length > 0 &&

                etvPassword.text.toString().length > 0 ) {

                var user = User(etvTitulo.text.toString() ,etvEmail.text.toString() ,etvUserName.text.toString(), etvPassword.text.toString()/*, etvPin.text.toString().toInt()*/)
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

        imageButtonarrow.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }




    }
}