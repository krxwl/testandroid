package com.github.krxwl.testandroid.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.krxwl.testandroid.entities.Frame

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertFrames(frame: List<Frame>)

    @Query("SELECT * FROM frames")
    fun getFrames(): LiveData<List<Frame>>

    @Query("DELETE FROM frames")
    fun clearFrames()

}