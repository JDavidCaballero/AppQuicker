package com.example.appkotlinnav.ui.ofimatica

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

class Fragmento_ofimtica : Fragment() {

    companion object {
        fun newInstance() = Fragmento_ofimtica()
    }

    private lateinit var viewModel: FragmentoOfimticaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragmento_ofimtica_fragment, container, false)

        val mywordbutton = root.findViewById<Button>(R.id.button3)

        mywordbutton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.docs.editors.docs"))
            startActivity(i)
        }

        val mytwbutton = root.findViewById<Button>(R.id.button5)

        mytwbutton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.docs.editors.sheets"))
            startActivity(i)
        }

        val mythibutton = root.findViewById<Button>(R.id.button6)


        mythibutton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.docs.editors.slides"))
            startActivity(i)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentoOfimticaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}