package com.github.krxwl.testandroid.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.github.krxwl.testandroid.Prefs
import com.github.krxwl.testandroid.Prefs.Companion.dataStore
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.database.Repository
import com.github.krxwl.testandroid.entities.Frame
import com.github.krxwl.testandroid.fragments.MainFragment
import com.github.krxwl.testandroid.viewmodels.MainViewModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://althgjdtmsvhoswaazdp.supabase.co",
        supabaseKey = "Ghostfuckers0910"
    ) {
        install(Postgrest)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Prefs()
        Repository.initialize(this)
        setTheme(R.style.Base_Theme_Testandroid)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstLaunch: Flow<Boolean> = applicationContext.dataStore.data.map { prefs ->
            prefs[Prefs.LAUNCH_KEY] ?: true
        }

        firstLaunch.asLiveData().observe(this) { firstlaunch ->
            if (firstlaunch) {
                lifecycleScope.launch {
                    setFirstLaunch()
                }
                viewModel.insertFrames()
            }
        }

        val onBoardingShow: Flow<Boolean> = applicationContext.dataStore.data
            .map { preferences ->
                preferences[Prefs.ONBOARDING_KEY] ?: false
            }

        onBoardingShow.asLiveData().observe(this) { isShow ->
            if (!isShow && viewModel.getIsActive() == false) {
                viewModel.setIsActive(true)
                startActivity(Intent(this, OnBoardingActivity::class.java))
            }
            if (viewModel.getIsActive() == false){
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }

        val currentFragment = supportFragmentManager.findFragmentById(R.id.activity_main)
        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_main, MainFragment())
                .commit()
        }
    }

    suspend fun setFirstLaunch() {
        applicationContext.dataStore.edit { prefs ->
            prefs[Prefs.LAUNCH_KEY] = false
        }
    }

    companion object {
        fun generateFrames(): List<Frame> {
            return listOf(
                Frame(
                    0, R.drawable.queue1,
                    R.string.queue_1_1_description,
                    R.string.queue_1_2_description
                ),
                Frame(
                    1, R.drawable.queue2,
                    R.string.queue_2_1_description,
                    R.string.queue_2_2_description
                ),
                Frame(
                    2, R.drawable.queue3,
                    R.string.queue_3_1_description,
                    R.string.queue_3_2_description
                )
            )
        }
    }

    override fun onStart() {
        super.onStart()
        val onBoardingShow: Flow<Boolean> = applicationContext.dataStore.data
            .map { preferences ->
                preferences[Prefs.ONBOARDING_KEY] ?: false
            }

        onBoardingShow.asLiveData().observe(this) { isShow ->
            if (isShow) {
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
    }

}

