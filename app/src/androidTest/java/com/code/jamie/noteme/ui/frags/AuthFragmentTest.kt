package com.code.jamie.noteme.ui.frags

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.Before
import org.junit.runner.RunWith
import com.code.jamie.noteme.R
import com.code.jamie.noteme.ui.activities.MainActivity
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class AuthFragmentTest {
    private lateinit var scenario: FragmentScenario<AuthFragment>

    @Before
    fun setUp() {
    }
    @Test
    fun viewPagerIsVisible(){
        assertNotNull(onView(withId(R.id.view_pager)))
    }
    @Test
    fun tabLayoutVisible(){
        assertNotNull(onView(withId(R.id.tab_layout)))
    }
}