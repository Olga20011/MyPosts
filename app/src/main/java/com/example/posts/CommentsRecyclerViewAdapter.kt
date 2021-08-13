package com.example.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsRecyclerViewAdapter(var commentList:List<CommentsDataClass>):RecyclerView.Adapter<commentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): commentsViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.comments_resource_file,parent,false)
        return commentsViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: commentsViewHolder, position: Int) {
        var currentComments=commentList.get(position)
        holder.name.text=currentComments.Name
        holder.email.text=currentComments.Email
        holder.body.text=currentComments.Body

    }

    override fun getItemCount(): Int {
        return commentList.size

    }
}

class commentsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var name=itemView.findViewById<TextView>(R.id.tvName)
    var email=itemView.findViewById<TextView>(R.id.tvEmail)
    var body=itemView.findViewById<TextView>(R.id.tvbody)
}