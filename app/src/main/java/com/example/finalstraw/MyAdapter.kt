package com.example.finalstraw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalstraw.model.Order

class MyAdapter(private val userlist: ArrayList<Order>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
        parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userlist[position]

        holder.sender.text = currentitem.consigner
        holder.receiver.text = currentitem.consignee
        holder.orderID.text = currentitem.uid
        holder.pickup.text = currentitem.pickup
        holder.destination.text = currentitem.destination

    }

    override fun getItemCount(): Int {

        return userlist.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val sender : TextView = itemView.findViewById(R.id.txtSender)
        val receiver : TextView = itemView.findViewById(R.id.txtReceiver)
        val pickup : TextView = itemView.findViewById(R.id.txtPickup)
        val destination : TextView = itemView.findViewById(R.id.txtDestination)
        val orderID : TextView = itemView.findViewById(R.id.txtId)
    }
}