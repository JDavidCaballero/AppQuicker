package com.example.appkotlinnav.ui.Dbase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appkotlinnav.DataBaseHandler
import com.example.appkotlinnav.R
import kotlinx.android.synthetic.main.activity_show_data.*

class ShowData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        val context = this
        var db = DataBaseHandler(context)

        btn_read.setOnClickListener{

            var data = db.readData()
            tvResult.text=""
            for (i in 0..(data.size-1)){

                tvResult.append(data.get(i).id.toString() + ". "+ "Nombre: " + data.get(i).name + " | " + "PIN: " + data.get(i).age + "\n\n")
            }
        }

        btn_update.setOnClickListener{

            db.updateData()
            btn_read.performClick()
        }

        btn_delete.setOnClickListener{

            db.deleteData()

            val intent = Intent(this, MainActivityDataBases::class.java)
            startActivity(intent);

        }


    }
}