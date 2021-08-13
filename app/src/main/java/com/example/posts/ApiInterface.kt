package com.example.posts


import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getPosts():Call<List<MyPost>>

    @GET("posts/{id}")
    fun getPostById(@Path("id")postId:Int):Call<MyPost>
    
    @GET("Posts/{postId}/comments")
    fun getComments(@Path("postId")id:Int):Call<List<CommentsDataClass>>
}