package com.example.appkotlinnav

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.security.AccessController.getContext
import java.util.ArrayList


val DataBase_Name = "MyDB"
val Table_Name = "Users"
val Col_Name = "name"
val Col_Age = "age"
val Col_Id = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DataBase_Name, null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        
        val createTable = "CREATE TABLE " + Table_Name + " (" +
                Col_Id +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_Name + " VARCHAR(256)," +
                Col_Age +" INTEGER)" ;

            db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun creatingtable(db: SQLiteDatabase?){

        val createTableifnotexixts = "CREATE TABLE IF NOT EXISTS " + Table_Name + " (" +
                Col_Id +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_Name + " VARCHAR(256)," +
                Col_Age +" INTEGER)" ;

        db?.execSQL(createTableifnotexixts)

    }


    fun insertData(user: User ){


        val db = this.writableDatabase


        creatingtable(db)


        var cv = ContentValues()
        cv.put(Col_Name, user.name)
        cv.put(Col_Age, user.age)
        var result = db.insert(Table_Name,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context, "Falló al insertar datos, intentelo mas tarde",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Se insertaron los datos correctamente",Toast.LENGTH_SHORT).show()
        }
    

    fun readData() : MutableList<User>{

        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + Table_Name
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()) {

            do{
                var user = User()
                user.id  = result.getString(result.getColumnIndex(Col_Id)).toInt()
                user.name  = result.getString(result.getColumnIndex(Col_Name))
                user.age  = result.getString(result.getColumnIndex(Col_Age)).toInt()
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

    fun updateData() {

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

    }

}