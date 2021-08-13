package com.example.posts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class PostsRecyclerViewAdapter(var postsList: List<MyPost>, var context:Context):RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return PostsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
 var currentPosts=postsList.get(position)
        holder.user.text=currentPosts.userId.toString()
        holder.id.text=currentPosts.id.toString()
        holder.title.text=currentPosts.title
        holder.body.text=currentPosts.body
        holder.clPost.setOnClickListener {
            var intent=Intent(context,ViewPostActivity::class.java)
            intent.putExtra("POST_ID",currentPosts.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
    return postsList.size
    }
}



class PostsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var user=itemView.findViewById<TextView>(R.id.tvUserId)
    var id=itemView.findViewById<TextView>(R.id.tvId)
    var title=itemView.findViewById<TextView>(R.id.tvTitle)
    var body=itemView.findViewById<TextView>(R.id.tvBody)
    var clPost=itemView.findViewById<ConstraintLayout>(R.id.clPost)
}