package com.example.myapplication

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userslist: List<UserEntity>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
      val username:TextView
      val userEmail:TextView
      init {
          username=itemView.findViewById(R.id.name)
          userEmail=itemView.findViewById(R.id.email)
      }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.itemrow,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.setText(userslist[position].name)
        holder.userEmail.setText(userslist[position].email)
    }

    override fun getItemCount()=userslist.size
}