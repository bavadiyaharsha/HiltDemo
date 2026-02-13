package com.example.hiltdemo.domain.usecase

import com.example.hiltdemo.data.Post
import com.example.hiltdemo.domain.repository.Repository
import javax.inject.Inject

class GetObjectUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Post> {
        return repository.getObjects()
    }
}

