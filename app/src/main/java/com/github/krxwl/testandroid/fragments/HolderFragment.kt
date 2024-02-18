package com.github.krxwl.testandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.databinding.HolderBinding

class HolderFragment : Fragment(R.layout.holder) {
    private lateinit var binding: HolderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HolderBinding.inflate(layoutInflater)
        return binding.root
    }
}