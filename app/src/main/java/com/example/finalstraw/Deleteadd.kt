package com.example.finalstraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalstraw.model.Order
import com.example.finalstraw.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Deleteadd : AppCompatActivity() {

    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<Order>
    private lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deleteadd)

        userList = ArrayList()

        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)

        userAdapter = UserAdapter(this,userList)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter
        addsBtn.setOnClickListener{addInfo()}

    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
              dialog,_->
            val names =userName.text.toString()
            val number = userNo.text.toString()
            userList.add(Order("Name: $names","UserID: $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Add Order Info Successful", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}