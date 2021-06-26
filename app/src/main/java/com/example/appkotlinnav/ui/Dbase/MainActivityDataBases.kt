package com.example.appkotlinnav.ui.Dbase

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appkotlinnav.*
import kotlinx.android.synthetic.main.activity_main_data_bases.*

class MainActivityDataBases : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_data_bases)

        val context = this



        btn_insert.setOnClickListener {


            if (etvName.text.toString().length > 0 &&
                etvAge.text.toString().length > 0) {
                var user = User(etvName.text.toString(), etvAge.text.toString().toInt())
                var db = DataBaseHandler(context)
                db.insertData(user)

                val intent = Intent(this, ShowData::class.java)
                startActivity(intent);
            }
                else {
                Toast.makeText(context, "Porfavor llene todos los campos", Toast.LENGTH_SHORT).show()
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