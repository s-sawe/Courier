package com.example.finalstraw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalstraw.databinding.ActivityAdminBinding
import com.google.firebase.auth.FirebaseAuth

class Admin : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        binding.one.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
    }
}