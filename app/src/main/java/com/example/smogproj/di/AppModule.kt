package com.example.smogproj.di

import android.app.Application
import androidx.room.Room
import com.example.smogproj.data.local.SmogDatabase
import com.example.smogproj.data.local.StationDao
import com.example.smogproj.data.remote.SmogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSmogApi(): SmogApi {
        return Retrofit.Builder()
            .baseUrl(SmogApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSmogDatabase(
        app: Application
    ): SmogDatabase {
        return Room.databaseBuilder(
            app,
            SmogDatabase::class.java,
            "smogdb.db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideStationDao(smogDatabase: SmogDatabase): StationDao = smogDatabase.stationDao

}