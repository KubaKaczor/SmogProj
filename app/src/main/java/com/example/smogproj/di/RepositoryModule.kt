package com.example.smogproj.di

import com.example.smogproj.data.repository.SmogRepositoryImpl
import com.example.smogproj.domain.repository.SmogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSmogRepository(
        smogRepositoryImpl: SmogRepositoryImpl
    ): SmogRepository
}