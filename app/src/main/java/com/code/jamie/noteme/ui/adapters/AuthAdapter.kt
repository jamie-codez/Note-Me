package com.code.jamie.noteme.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.code.jamie.noteme.models.FragObject

class AuthAdapter(
    private val list: List<FragObject>,
    private val fm: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(
        fm,
        lifecycle
    ) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position].frag

}