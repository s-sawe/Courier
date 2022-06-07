package com.example.finalstraw.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.finalstraw.R
import com.example.finalstraw.model.Order
import java.nio.file.Files.delete

class UserAdapter(val c: Context, val userList:ArrayList<Order>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val v:View) : RecyclerView.ViewHolder(v) {
        var consigner:TextView
        var consignee:TextView
        var mMenus: ImageView
        init{
             consigner = v.findViewById<TextView>(R.id.mTitle)
             consignee = v.findViewById<TextView>(R.id.SubTitle)
             mMenus = v.findViewById(R.id.mMenus)
             mMenus.setOnClickListener { popupMenus(it)}
        }

        private fun popupMenus(v:View){
            val position = userList[adapterPosition]
             val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener{
                when(it.itemId){
                    R.id.editText->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_item, null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val number = v.findViewById<EditText>(R.id.userNo)
                            AlertDialog.Builder(c)
                                .setView(v)
                                .setPositiveButton("Ok"){
                                    dialog,_->
                                    position.consigner = name.text.toString()
                                    position.consignee = number.text.toString()
                                    notifyDataSetChanged()
                                    Toast.makeText(c,"Info Edited",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("Cancel"){
                                    dialog,_->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()

                        true
                    }
                    R.id.delete->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_problem)
                            .setMessage("Confirm Delete?")
                            .setPositiveButton("Yes"){
                                dialog,_->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"Info Deleted",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else-> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list,parent,false)
        return UserViewHolder(v)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val newList = userList[position]
        holder.consigner.text = newList.consigner
        holder.consignee.text = newList.consignee

    }

    override fun getItemCount(): Int {

        return userList.size

    }

}