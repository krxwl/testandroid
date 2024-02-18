package com.github.krxwl.testandroid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krxwl.testandroid.database.Repository
import com.github.krxwl.testandroid.entities.Frame
import com.github.krxwl.testandroid.fragments.OnBoardingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingViewModel : ViewModel() {
    private var repository: Repository = Repository.get()

    private var currentFrame = 0
    var frames: LiveData<List<Frame>> = takeFrames()
    var framesList: List<Frame> = listOf()

    fun setCurrentFrame(index: Int) {
        currentFrame = index
    }
    fun getCurrentFrame(): Int {
        return currentFrame
    }

    fun clearFrames() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearFrames()
    }
    fun takeFrames(): LiveData<List<Frame>> = repository.getFrames()
    /*fun insertFrames() = viewModelScope.launch(Dispatchers.IO) {
        repository.insertFrames(OnBoardingFragment.generateFrames())
    }*/

    fun framesCount(): Int {
        return framesList.size
    }
}