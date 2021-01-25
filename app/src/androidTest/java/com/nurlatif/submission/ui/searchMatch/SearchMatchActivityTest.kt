package com.nurlatif.submission.ui.searchMatch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import com.nurlatif.submission.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchMatchActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SearchMatchActivity::class.java)

    @Test
    fun testSearchBehavior() {

        onView(withId(R.id.searchMatch)).check(matches(isDisplayed()))
        onView(withId(R.id.searchMatchRv)).check(matches(isDisplayed()))

        onView(withId(R.id.searchMatch)).perform(click())
        onView(withId(R.id.searchMatch)).perform(typeText("manchester"))
        onView(withId(R.id.searchMatch)).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        Thread.sleep(5000)
        onView(withId(R.id.searchMatchRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )

        onView(withId(R.id.cardView2)).check(matches(isDisplayed()))
        pressBack()

    }


}