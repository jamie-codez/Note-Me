package com.code.jamie.noteme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}