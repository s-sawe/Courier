package com.example.finalstraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalstraw.databinding.ActivityCurrentBinding
import com.example.finalstraw.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class Current : AppCompatActivity() {

    private lateinit var binding: ActivityCurrentBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener{
            //Generate key
            val orderid = UUID.randomUUID().toString()
            val uid = FirebaseAuth.getInstance().uid
            val consignee = binding.Consignee.text.toString()
            val consigner = binding.Consigner.text.toString()
            val idNo = binding.Id.text.toString()
            val pickup = binding.Pickup.text.toString()
            val destination = binding.Destination.text.toString()
            val status = "Pending"

            database = FirebaseDatabase.getInstance().getReference("Order")
            val Orders = Order(uid,orderid,consigner,consignee,idNo,pickup,destination,status)
            database.child(consigner).setValue(Orders).addOnSuccessListener{

                binding.Consignee.text.clear()
                binding.Consigner.text.clear()
                binding.Id.text.clear()
                binding.Destination.text.clear()
                binding.Pickup.text.clear()

                Toast.makeText(this, "Delivery Request Successful", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{

                Toast.makeText(this, "Delivery Request Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}