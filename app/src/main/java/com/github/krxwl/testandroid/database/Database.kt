package com.github.krxwl.testandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.krxwl.testandroid.entities.Frame

@Database(entities = [Frame::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dao(): DatabaseDao
}