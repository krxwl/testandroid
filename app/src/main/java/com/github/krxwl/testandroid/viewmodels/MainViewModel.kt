package com.github.krxwl.testandroid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krxwl.testandroid.activities.MainActivity.Companion.generateFrames
import com.github.krxwl.testandroid.database.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = Repository.get()

    fun insertFrames() = viewModelScope.launch(Dispatchers.IO) {
        repository.insertFrames(generateFrames())
    }
}