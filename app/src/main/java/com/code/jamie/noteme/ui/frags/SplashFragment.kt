package com.code.jamie.noteme.ui.frags

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var binding:FragmentSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: $TAG started successfully")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        private val TAG = SplashFragment::class.java.simpleName
    }
}