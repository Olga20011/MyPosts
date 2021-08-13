package com.example.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var postView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postView=findViewById(R.id.rvPosts)

        fetchPosts()

    }
    fun fetchPosts(){
        var retrofit=ApiClient.buildService(ApiInterface::class.java)
        var request=retrofit.getPosts()
        request.enqueue(object : Callback<List<MyPost>> {
            override fun onResponse(call: Call<List<MyPost>>, response: Response<List<MyPost>>) {
               if (response.isSuccessful){
                   var postList=response.body()!!
                   var postAdapter=PostsRecyclerViewAdapter(postList,baseContext)
                   postView.adapter=postAdapter
                    postView.layoutManager=LinearLayoutManager(baseContext)
                  Toast.makeText(baseContext,postList!!.size.toString(),Toast.LENGTH_LONG).show()
               }
            }

            override fun onFailure(call: Call<List<MyPost>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()

        }


        }) 
    }
}

//if (postlist!=null){
//var