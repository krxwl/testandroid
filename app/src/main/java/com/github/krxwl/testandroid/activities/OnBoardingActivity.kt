package com.github.krxwl.testandroid.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.databinding.OnboardingActivityBinding
import com.github.krxwl.testandroid.fragments.OnBoardingFragment

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: OnboardingActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OnboardingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentFragment = supportFragmentManager.findFragmentById(R.id.onboarding_activity)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.onboarding_activity, OnBoardingFragment())
                .commit()
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finishAffinity()
                }
            }
        )
    }
}
