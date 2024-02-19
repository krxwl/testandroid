package com.github.krxwl.testandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.krxwl.testandroid.R
import com.github.krxwl.testandroid.databinding.SignInFragmentBinding

class HolderFragment : Fragment(R.layout.sign_in_fragment) {
    private lateinit var binding: SignInFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}