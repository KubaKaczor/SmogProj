package com.example.smogproj.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [StationEntity::class],
    version = 1
)
abstract class SmogDatabase: RoomDatabase() {

    abstract val stationDao: StationDao
}