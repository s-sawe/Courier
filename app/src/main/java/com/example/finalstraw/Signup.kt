package com.example.finalstraw

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.finalstraw.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {


    private lateinit var binding : ActivitySignupBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signintxt.setOnClickListener{
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }
        //ProgressDialog configs
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Sign Up")
        progressDialog.setMessage("Verifying credentials, Please Wait... ")
        progressDialog.setCanceledOnTouchOutside(false)
        //initialize firebase
        firebaseAuth = FirebaseAuth.getInstance()
        //begin sign up process when sign up button is clicked
        binding.btnRegister.setOnClickListener {
            validateUser()
        }

    }

    private fun validateUser() {
        email = binding.emailtxt.text.toString()
        password = binding.passwordtxt.text.toString()

        // validate user data

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailtxt.error = "Enter a valid emial"
        }
        else if (TextUtils.isEmpty(password)){
            binding.passwordtxt.error = "Password is required"
        } else if (password.length < 6){
            binding.passwordtxt.error = "Password must be at least 6 charaters"
        }
        else {
            //User data is valid
            userSignup()
        }
    }

    private fun userSignup() {
        progressDialog.show()

        //Register user to database
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Sign up successful. Congratulations!!", Toast.LENGTH_SHORT).show()

                //open the dashboard
                startActivity(Intent(this, Home::class.java))
                finish()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, "Sign up failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }

    override fun onSupportNavigateUp(): Boolean{
        onBackPressed()
        return  super.onNavigateUp()
    }




}







