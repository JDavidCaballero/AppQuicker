package com.example.appkotlinnav

class User {

    var id : Int = 0

    var nombreapp : String =  ""

    var email : String = ""

    var username : String = ""

    var password : String = ""

    /*var pin : Int = 0*/

    constructor(nombreapp : String,email : String , username:String, password : String /*, pin : Int*/) {

        this.nombreapp = nombreapp

        this.email = email

        this.username = username

        this.password = password

        /*this.pin = pin*/


    }

    constructor(){




    }
}