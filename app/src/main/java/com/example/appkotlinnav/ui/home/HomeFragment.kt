package com.example.appkotlinnav.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appkotlinnav.R
import com.example.appkotlinnav.databinding.FragmentHomeBinding
import com.example.appkotlinnav.ui.redessociales.FragmentoNuevoUno


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val myfrstbutton = root.findViewById<ImageButton>(R.id.imageButton)

        myfrstbutton.setOnClickListener {

        val action = HomeFragmentDirections.actionNavHomeToNavRedes()
            findNavController().navigate(action)

        }

        val mytwotbutton = root.findViewById<ImageButton>(R.id.imageButton2)

        mytwotbutton.setOnClickListener {

            val action = HomeFragmentDirections.actionNavHomeToNavEntretenimiento()
            findNavController().navigate(action)

        }

        val mythrtbutton = root.findViewById<ImageButton>(R.id.imageButton3)

        mythrtbutton.setOnClickListener {

            val action = HomeFragmentDirections.actionNavHomeToNavMensajeria()
            findNavController().navigate(action)

        }

        val myfourthtbutton = root.findViewById<ImageButton>(R.id.imageButton4)

        myfourthtbutton.setOnClickListener {

            val action = HomeFragmentDirections.actionNavHomeToFragmentoOfimtica()
            findNavController().navigate(action)

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}