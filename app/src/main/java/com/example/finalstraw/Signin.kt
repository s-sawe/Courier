package com.example.finalstraw


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.finalstraw.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class Signin : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding;

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password =""

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ProgressDialog configs
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Signing in")
        progressDialog.setMessage("Verifying credentials, Please Wait... ")
        progressDialog.setCanceledOnTouchOutside(false)

        //initialize firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //Open register activity
        binding.SignUp.setOnClickListener{
            startActivity(Intent(this, Signup::class.java))

        }
        //Login process
        binding.btnSignin.setOnClickListener {
            validateUser()
        }

    }

    private fun validateUser() {
        email = binding.emailtxt.text.toString()
        password = binding.passwordtxt.text.toString()

        //validate user details
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //wron email format
            binding.emailtxt.error = "Wrong email format"
        }
        else if (TextUtils.isEmpty(password)){
            binding.passwordtxt.error = "Kindly type your password"
        }
        else{
            //user details is validated begin log in
            userLogin()
        }


    }

    private fun userLogin() {
        //display dialog
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Sign in Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Home::class.java))
                finish()


            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Sign in failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        //if user is already logged in go to home activity
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null){
            startActivity(Intent(this, Home::class.java))
            finish()
        }
    }

    }



