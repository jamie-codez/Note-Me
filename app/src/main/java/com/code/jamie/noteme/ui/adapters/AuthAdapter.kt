package com.code.jamie.noteme.ui.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.code.jamie.noteme.vo.obj.FragObject

class AuthAdapter(private val list: List<FragObject>, fm: FragmentManager) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int) = list[position].frag

    override fun getPageTitle(position: Int) = list[position].name
}