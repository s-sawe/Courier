package com.example.finalstraw

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalstraw.databinding.ActivityPlaceBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import android.text.TextUtils

class Place : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceBinding
    private lateinit var database: DatabaseReference


    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        binding = ActivityPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getbutton.setOnClickListener {
            val consigner: String = binding.orderid.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Order")
            database.child(consigner).get().addOnSuccessListener {

                if (it.exists()) {
                    val sender = it.child("consigner").value
                    val consignee = it.child("consignee").value
                    val pickup = it.child("pickup").value
                    val destination = it.child("destination").value
                    val status = it.child("status").value

                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                    //display data in the text area
                    binding.txtread.text = "sender : $sender \n" +
                            " Recipient : $consignee \n " +
                            "Pick up point : $pickup \n " +
                            "Destination: $destination \n " +
                            "Status : $status"
                    //Clear search field
                    binding.orderid.text.clear()


                } else {
                    Toast.makeText(this, "User Does Not Exist", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }
        }
        binding.btnView.setOnClickListener{
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
    }
}


