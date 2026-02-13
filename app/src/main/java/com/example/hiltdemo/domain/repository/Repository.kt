package com.example.hiltdemo.domain.repository

import com.example.hiltdemo.data.Post

interface Repository {
    suspend fun getObjects(): List<Post>

}
