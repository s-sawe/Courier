package com.example.finalstraw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalstraw.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.one.setOnClickListener {
            val intent = Intent(this, Current::class.java)
            startActivity(intent)
        }
        binding.two.setOnClickListener {
            val intent = Intent(this, Place::class.java)
            startActivity(intent)
        }
        binding.six.setOnClickListener {
            val intent = Intent(this, Issue::class.java)
            startActivity(intent)

        }
        binding.three.setOnClickListener{
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
        binding.four.setOnClickListener{
            val intent = Intent(this, Deleteadd::class.java)
            startActivity(intent)
        }
        binding.logouttv.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }

        //initialize firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

    }

    private fun checkUser() {
        //check if user is logged in or not
        val firebaseuser = firebaseAuth.currentUser
        if (firebaseuser != null){
            val email = firebaseuser!!.email

            binding.username.text = email
        }
        else {
            startActivity(Intent(this, Signin::class.java))
            finish()
        }
    }

}