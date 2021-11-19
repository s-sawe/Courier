package com.example.courierapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


class Splash : Fragment() {

    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splash()
        rootView = inflater.inflate(R.layout.fragment_splash, container, false)
        return rootView
    }

    fun splash(){
        Handler(Looper.getMainLooper()).postDelayed(Runnable { findNavController().navigate(R.id.action_splash_to_signUp)
        },500)

    }
}