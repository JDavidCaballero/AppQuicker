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

        btn_updatetable.setOnClickListener {




            if (etvSelectId.text.toString().length > 0 &&

                etvUpdatedName.text.toString().length > 0 &&

                etvUpdatedAge.text.toString().length > 0) {

                var id = etvSelectId.text.toString().toInt()

                var user = User(etvUpdatedName.text.toString(), etvUpdatedAge.text.toString().toInt())

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