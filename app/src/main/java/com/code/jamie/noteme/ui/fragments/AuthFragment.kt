package com.code.jamie.noteme.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.code.jamie.noteme.databinding.FragmentAuthBinding
import com.code.jamie.noteme.ui.adapters.AuthAdapter
import com.code.jamie.noteme.vo.obj.FragObject


class AuthFragment : Fragment() {
    private lateinit var binding:FragmentAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragList = ArrayList<FragObject>()
        fragList.add(FragObject("Login",LoginFragment()))
        fragList.add(FragObject("SignUp",SignUpFragment()))
        val adapter = AuthAdapter(fragList,requireActivity().supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

}