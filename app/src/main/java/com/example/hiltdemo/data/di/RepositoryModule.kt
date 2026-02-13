package com.example.hiltdemo.data.di

import com.example.hiltdemo.data.remote.repository.RepositoryImp
import com.example.hiltdemo.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImp: RepositoryImp
    ): Repository

}