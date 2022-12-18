package com.code.jamie.noteme.ui.frags

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import com.code.jamie.noteme.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashFragmentTest{
    private lateinit var scenario: FragmentScenario<SplashFragment>

    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_NoteMe_FullScreen)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun logoIsVisible(){
        assertNotNull(onView(withId(R.id.logo_iv)))
    }
    @Test
    fun logoTextIsVisible(){
        assertNotNull(onView(withId(R.id.logo_tv)))
    }
}