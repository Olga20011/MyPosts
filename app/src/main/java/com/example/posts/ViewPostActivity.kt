package com.example.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.ActivityViewPostBinding
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewPostActivity : AppCompatActivity() {
    lateinit var rvCommments: RecyclerView
    lateinit var binding: ActivityViewPostBinding
    var postId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postId = intent.getIntExtra("POST_ID", 0)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getPost()
        getComments()
    }

    fun getPost() {
        var retrofit = ApiClient.buildService(ApiInterface::class.java)
        var request = retrofit.getPostById(postId)
        request.enqueue(object : Callback<MyPost> {
            override fun onResponse(call: Call<MyPost>, response: Response<MyPost>) {
                if (response.isSuccessful) {
                    binding.tvTitle.text = response.body()?.title
                    binding.tvBody.text = response.body()?.body

                }
            }

            override fun onFailure(call: Call<MyPost>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }

        })


    }

    fun getComments() {
        var rvComments = findViewById<RecyclerView>(R.id.rvComments)
        var retrofit = ApiClient.buildService(ApiInterface::class.java)
        var request = retrofit.getComments(postId)
        request.enqueue(object : Callback<List<CommentsDataClass>> {
            override fun onResponse(
                call: Call<List<CommentsDataClass>?>,
                response: Response<List<CommentsDataClass>?>
            ) {
                if (response.isSuccessful) {
                    var comments = response.body()!!

                    var commentsAdapter = CommentsRecyclerViewAdapter(comments)
                    rvComments.adapter = commentsAdapter
                    rvComments.layoutManager = LinearLayoutManager(baseContext)

                }
            }

            override fun onFailure(call: Call<List<CommentsDataClass>?>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()


            }
        })
    }
}
