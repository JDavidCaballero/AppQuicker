package com.example.appkotlinnav

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_update_dbase.*
import java.security.AccessController.getContext
import java.util.ArrayList


val DataBase_Name = "MyDB"
val Table_Name = "Users"
val Col_nombreApp = "nombreapp"
val Col_Email = "email"
val Col_UserName = "username"
val Col_Password = "password"
/*val Col_Pin = "pin"*/
val Col_Id = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DataBase_Name, null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        
        val createTable = "CREATE TABLE " + Table_Name + " (" +
                Col_Id +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_nombreApp + " VARCHAR(256)," +
                Col_UserName + " VARCHAR(256)," +
                Col_Email + " VARCHAR(256)," +
                Col_Password + " VARCHAR(256)" + " )" ; 
                /*Col_Pin +" INTEGER)"*/

            db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun creatingtable(db: SQLiteDatabase?){

        val createTableifnotexixts = "CREATE TABLE IF NOT EXISTS " + Table_Name + " (" +
                Col_Id +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_nombreApp + " VARCHAR(256)," +
                Col_UserName + " VARCHAR(256)," +
                Col_Email + " VARCHAR(256)," +
                Col_Password + " VARCHAR(256)" + " )" ;
                /*Col_Pin +" INTEGER)"*/

        db?.execSQL(createTableifnotexixts)

    }


    fun insertData(user: User ){



        val db = this.writableDatabase
        var cv = ContentValues()
        creatingtable(db)

        cv.put(Col_nombreApp, user.nombreapp)
        cv.put(Col_Email, user.email)
        cv.put(Col_UserName, user.username)
        cv.put(Col_Password, user.password)
        /*cv.put(Col_Pin, user.pin)*/

        var result = db.insert(Table_Name,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context, "Falló al insertar datos, intentelo mas tarde",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Se insertaron los datos correctamente",Toast.LENGTH_SHORT).show()
        }
    

    fun readData() : MutableList<User>{

       // val Tableifnotexixts = "IF NOT EXISTS " + Table_Name



        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + Table_Name
        val result = db.rawQuery(query,null)

        

        if(result.moveToFirst()) {

            do{
                var user = User()
                user.id  = result.getString(result.getColumnIndex(Col_Id)).toInt()
                user.nombreapp = result.getString(result.getColumnIndex(Col_nombreApp))
                user.email = result.getString(result.getColumnIndex(Col_Email))
                user.username  = result.getString(result.getColumnIndex(Col_UserName))
                user.password = result.getString(result.getColumnIndex(Col_Password))
               /* user.pin  = result.getString(result.getColumnIndex(Col_Pin)).toInt()*/
                list.add(user)

            }while (result.moveToNext())

        }
        result.close()
        db.close()
        return list
    }

    fun deleteData(){

        val db = this.writableDatabase

        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name);

        db.close()
    }

    fun updateTableByAppName(user : User, nombreapp : String){

        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(Col_nombreApp, user.nombreapp)
        cv.put(Col_Email, user.email)
        cv.put(Col_UserName, user.username)
        cv.put(Col_Password, user.password)
       /* cv.put(Col_Pin, user.pin)*/

        var resultUp = db.update(Table_Name, cv, "$Col_nombreApp=?", arrayOf(nombreapp))

        if(resultUp <= 0) {
            Toast.makeText(
                context,
                "Fallo al Actualizar datos / no se encontró el nombre de esa App",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            Toast.makeText(context, "Se Actualizaron los datos correctamente", Toast.LENGTH_SHORT)
                .show()
        }
    }

    //solo autoincrementa el nnumero de pin o col_age
    /*fun updateData() {

        val db = this.writableDatabase
        val query = "Select * from " + Table_Name
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()) {

            do{
                var cv = ContentValues ()
                cv.put(Col_Age, (result.getInt(result.getColumnIndex(Col_Age))+1))
                db.update(Table_Name,cv, Col_Id + "=? AND " + Col_Name + "=?",
                    arrayOf(result.getString(result.getColumnIndex(Col_Id)),
                        result.getString(result.getColumnIndex(Col_Name))))


            }while (result.moveToNext())

        }
        result.close()
        db.close()

    }*/

}