package com.github.krxwl.testandroid.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.github.krxwl.testandroid.entities.Frame
import java.lang.IllegalStateException

class Repository private constructor(context: Context){

    private val database: Database = Room.databaseBuilder(context.applicationContext,
        Database::class.java,
        "database.db").build()

    private val dao = database.dao()

    fun insertFrames(frames: List<Frame>) = dao.insertFrames(frames)

    fun clearFrames() = dao.clearFrames()

    fun getFrames(): LiveData<List<Frame>> = dao.getFrames()

    companion object {
        private var repository: Repository? = null

        fun initialize(context: Context) {
            if (repository == null) {
                Log.i("1234", "INIT REPO")
                repository = Repository(context)
            }
        }

        fun get(): Repository {
            return repository ?: throw IllegalStateException("Repo must be init")
        }
    }
}