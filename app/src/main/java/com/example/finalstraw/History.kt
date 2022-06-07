package com.example.finalstraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalstraw.model.Order
import com.google.firebase.database.*

class History : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var orderRecyclerView: RecyclerView
    private lateinit var orderArrayList: ArrayList<Order>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        orderRecyclerView = findViewById(R.id.userlist)
        orderRecyclerView.layoutManager = LinearLayoutManager(this)
        orderRecyclerView.setHasFixedSize(true)

        orderArrayList = java.util.ArrayList<Order>()
        getUserData()
       

    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Order")
        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(OrderSnapshot in snapshot.children){
                        val history = OrderSnapshot.getValue(Order:: class.java)
                        orderArrayList.add(history!!)

                    }
                    orderRecyclerView.adapter = MyAdapter(orderArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}