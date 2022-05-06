package com.example.tiptime

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_25_percent_tip() {
        onView(withId(R.id.cost_of_service))
            .perform(typeText("123.00"))

        onView(withId(R.id.calculate_tip_button))
            .perform(click())

        onView(withId(R.id.tip_amount))
            .check(matches(withText(containsString("$30.75"))))
    }

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service))
            .perform(typeText("123"))

        onView(withId(R.id.good_service))
            .perform(click())

        onView(withId(R.id.calculate_tip_button))
            .perform(click())

        onView(withId(R.id.tip_amount))
            .check(matches(withText(containsString("$24.60"))))
    }

    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.cost_of_service))
            .perform(typeText("123"))

        onView(withId(R.id.normal_service))
            .perform(click())

        onView(withId(R.id.calculate_tip_button))
            .perform(click())

        onView(withId(R.id.tip_amount))
            .check(matches(withText(containsString("$18.45"))))
    }
}