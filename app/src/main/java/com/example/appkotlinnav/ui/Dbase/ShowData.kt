package com.example.appkotlinnav.ui.Dbase

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.appkotlinnav.DataBaseHandler
import com.example.appkotlinnav.MainActivity
import com.example.appkotlinnav.R
import com.example.appkotlinnav.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_show_data.*

class ShowData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.Azul)
        }

        val context = this
        var db = DataBaseHandler(context)

        btn_read.setOnClickListener{

            var data = db.readData()
            tvResult.text=""
            for (i in 0..(data.size-1)){

                tvResult.append(data.get(i).id.toString() + ". "+ "Nombre de la App: " + data.get(i).nombreapp +"\n" + " | "+ "Email: "+ data.get(i).email + "\n" + " | "+ "Nombre de usuario: " + data.get(i).username + "\n" + " | " + "Password: " + data.get(i).password /*+ " | " + "PIN : " + data.get(i).pin*/ + "\n\n")
            }
        }

        btn_update.setOnClickListener{

            val intent = Intent(this, UpdateDbase::class.java)
            startActivity(intent)

        }

        btn_delete.setOnClickListener{

            db.deleteData()

            val intent = Intent(this, MainActivityDataBases::class.java)
            startActivity(intent);

        }

        imageButtonarrow.setOnClickListener{

            val intent = Intent(this, MainActivityDataBases::class.java)
            startActivity(intent)
        }

        imageButtonhouse.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}