package com.github.krxwl.testandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.databinding.MainFragBinding

class MainFragment : Fragment(R.layout.main_frag) {

    private lateinit var binding: MainFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragBinding.inflate(layoutInflater)
        return binding.root
    }
}