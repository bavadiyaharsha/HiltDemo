package com.example.hiltdemo.data.remote

import com.example.hiltdemo.data.Post
import retrofit2.http.GET

interface RetrofitServiceApi {

    @GET("/objects")
    suspend fun getObjects():  List<Post>
}
