package com.example.hiltdemo.data.remote.repository

import com.example.hiltdemo.data.Post
import com.example.hiltdemo.data.remote.RetrofitServiceApi
import com.example.hiltdemo.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor ( private val api: RetrofitServiceApi) : Repository {
    override suspend fun getObjects(): List<Post> = api.getObjects()
}
