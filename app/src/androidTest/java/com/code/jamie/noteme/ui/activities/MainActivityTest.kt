package com.code.jamie.noteme.ui.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.code.jamie.noteme.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    @get:Rule
    val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun activityHasViews(){
        assertNotNull(onView(withId(R.id.main_activity_nav_host)))
    }
}