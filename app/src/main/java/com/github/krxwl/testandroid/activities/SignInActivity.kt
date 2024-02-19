package com.github.krxwl.testandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.databinding.SignInActivityBinding
import com.github.krxwl.testandroid.fragments.HolderFragment

class SignInActivity : AppCompatActivity() {
    lateinit var binding: SignInActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.auth_activity)
        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.auth_activity, HolderFragment()).
                commit()
        }
    }

    override fun finish() {
        super.finish()
        this.finishAffinity()
    }

}