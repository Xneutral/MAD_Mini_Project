package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.HomeActivity
import com.example.assignment.R
import com.example.assignment.datamodel.DataModel
import com.google.firebase.database.FirebaseDatabase


class MyAdapter(private val userList: ArrayList<DataModel>): RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rec_view_layout,parent,false)
        return MyViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= userList[position]
        val ha=HomeActivity()
        holder.name.text =currentItem.name
        holder.email.text =currentItem.email
        holder.phoneNo.text =currentItem.phoneNo
        }

    override fun getItemCount(): Int {
       return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name:TextView =itemView.findViewById(R.id.c_name)
        val email:TextView =itemView.findViewById(R.id.c_email)
        val phoneNo:TextView =itemView.findViewById(R.id.c_mobile)
    }

}