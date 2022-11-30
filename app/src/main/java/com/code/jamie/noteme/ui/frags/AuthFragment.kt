package com.code.jamie.noteme.ui.frags

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.code.jamie.noteme.databinding.FragmentAuthBinding
import com.code.jamie.noteme.models.FragObject
import com.code.jamie.noteme.ui.adapters.AuthAdapter
import com.google.android.material.tabs.TabLayoutMediator

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: $TAG started successfully")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = ArrayList<FragObject>()
        list.add(FragObject("LOGIN", LoginFragment()))
        list.add(FragObject("REGISTER", RegisterFragment()))
        val adapter = AuthAdapter(list, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = list[position].title
                1 -> tab.text = list[position].title
            }
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private val TAG = AuthFragment::class.java.simpleName
    }
}