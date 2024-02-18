package com.github.krxwl.testandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.databinding.AuthActivityBinding
import com.github.krxwl.testandroid.fragments.HolderFragment

class AuthActivity : AppCompatActivity() {
    lateinit var binding: AuthActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthActivityBinding.inflate(layoutInflater)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.auth_activity)
        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.auth_activity, HolderFragment()).
                commit()
        }
    }
}