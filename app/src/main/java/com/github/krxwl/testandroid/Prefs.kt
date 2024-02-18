package com.github.krxwl.testandroid

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class Prefs {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "onboarding")
        val ONBOARDING_KEY = booleanPreferencesKey("onboarding")
        val LAUNCH_KEY = booleanPreferencesKey("applaunch")
        val ONBOARDING_SLIDE = intPreferencesKey("onboardingslide")
    }
}