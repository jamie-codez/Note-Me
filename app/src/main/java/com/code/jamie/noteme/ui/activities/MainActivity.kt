package com.code.jamie.noteme.ui.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.code.jamie.noteme.R
import com.code.jamie.noteme.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var jwt: String
    override fun onStart() {
        super.onStart()
        val sharedPreferences = Utils.getPrefs(this)
        jwt = Utils.getJWT(Utils.jwt_pref, sharedPreferences)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.findNavController()
        Handler(Looper.getMainLooper()).postDelayed({
            if (jwt.isBlank()) {
                navController.navigate(R.id.action_splashFragment_to_authFragment)
            } else {
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
                finish()
            }
        }, 2000)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_activity_nav_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}