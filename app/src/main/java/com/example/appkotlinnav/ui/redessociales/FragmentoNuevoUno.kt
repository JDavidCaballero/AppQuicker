package com.example.appkotlinnav.ui.redessociales

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.appkotlinnav.R

class FragmentoNuevoUno : Fragment() {



    companion object {
        fun newInstance() = FragmentoNuevoUno()
    }

    private lateinit var viewModel: FragmentoNuevoUnoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {

// se cambió el root a una variable para que no declarar en el return y luego el click event de los botones

        val root = inflater.inflate(R.layout.fragmento_nuevo_uno_fragment, container, false)

        val myinbutton = root.findViewById<Button>(R.id.button3)

        myinbutton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android"))
            startActivity(i)
        }

        val mytwbutton = root.findViewById<Button>(R.id.button5)

        mytwbutton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.twitter.android"))
            startActivity(i)
        }

        val mythibutton = root.findViewById<Button>(R.id.button6)


        mythibutton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.reddit.frontpage"))
            startActivity(i)
        }


        return root



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentoNuevoUnoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}