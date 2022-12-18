package com.code.jamie.noteme.ui.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationListener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
        navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.navigationLayout.setupWithNavController(navController)
        val activity = activity as AppCompatActivity
        activity.setupActionBarWithNavController(navController, binding.drawerLayout)
        navigationListener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.notesFragment -> activity.supportActionBar!!.title = "Notes"
                    R.id.editProfileFragment -> activity.supportActionBar!!.title = "Update Profile"
                }
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(navigationListener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(navigationListener)
    }


    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }
}