package com.code.jamie.noteme.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}