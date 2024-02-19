package com.github.krxwl.testandroid.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.github.krxwl.testandroid.Prefs
import com.github.krxwl.testandroid.Prefs.Companion.LAUNCH_KEY
import com.github.krxwl.testandroid.Prefs.Companion.ONBOARDING_SLIDE
import com.github.krxwl.testandroid.Prefs.Companion.dataStore
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.databinding.OnboardingFragmentBinding
import com.github.krxwl.testandroid.entities.Frame
import com.github.krxwl.testandroid.viewmodels.OnBoardingViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
class OnBoardingFragment : Fragment(R.layout.onboarding_fragment) {
    lateinit var binding: OnboardingFragmentBinding
    val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OnboardingFragmentBinding.inflate(layoutInflater)

        binding.nextBtn.setOnClickListener {
            lifecycleScope.launch {
                val oldIndex = onBoardingViewModel.getCurrentFrame()
                onBoardingViewModel.framesList.drop(oldIndex)
                reloadFrame(onBoardingViewModel.framesList[oldIndex + 1])
            }
        }

        binding.skipBtn.setOnClickListener {
            lifecycleScope.launch {
                checkedOnBoard()
                activity?.finish()
            }
        }

        binding.signUpBtn.setOnClickListener {
            lifecycleScope.launch {
                checkedOnBoard()
                activity?.finish()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBoardingViewModel.frames.observe(viewLifecycleOwner) { frames ->
            lifecycleScope.launch {
                if (!frames.isEmpty()) {
                    onBoardingViewModel.framesList = frames
                    val onBoardingSlide: Flow<Int> =
                        requireContext().dataStore.data.map { preferences ->
                            preferences[ONBOARDING_SLIDE] ?: 0
                        }

                    onBoardingSlide.asLiveData().observe(viewLifecycleOwner) { slide ->
                        if (slide != 2) {
                            binding.signUpBtn.visibility = View.GONE
                            binding.signInTextview.visibility = View.GONE
                            binding.textview.visibility = View.GONE
                        }
                        reloadFrame(frames.find { it.id == slide }!!)
                    }
                }
            }
        }
    }

    fun animateFadeOut() {
        binding.descriptionTextview.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))
        binding.propertyTextview.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))
        binding.onboardingImage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))
    }
    suspend fun checkedOnBoard() {
        requireContext().dataStore.edit { preferences ->
            preferences[Prefs.ONBOARDING_KEY] = true
        }
        activity?.finish()
        clearQueue()
    }

    fun clearQueue() {
        onBoardingViewModel.clearFrames()
    }

    fun reloadFrame(frame: Frame) {
        onBoardingViewModel.setCurrentFrame(frame.id)
        if (onBoardingViewModel.getCurrentFrame() == 2) {
            binding.signUpBtn.visibility = View.VISIBLE
            binding.signInTextview.visibility = View.VISIBLE
            binding.textview.visibility = View.VISIBLE
            binding.skipBtn.visibility = View.GONE
            binding.nextBtn.visibility = View.GONE
        }
        binding.onboardingImage.setImageDrawable(resources.getDrawable(frame.bitmap))
        binding.propertyTextview.setText(resources.getString(frame.firstDescription))
        binding.descriptionTextview.setText(resources.getString(frame.secondDescription))
        animateFadeOut()
    }

    suspend fun saveSlide() {
        requireContext().dataStore.edit { preferences ->
            preferences[Prefs.ONBOARDING_SLIDE] = onBoardingViewModel.getCurrentFrame()
        }
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.launch {
            saveSlide()
        }
    }
}