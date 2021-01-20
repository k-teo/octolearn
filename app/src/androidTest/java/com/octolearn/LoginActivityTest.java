package com.octolearn;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class LoginActivityTest {
    @Rule
    public final ActivityScenarioRule rule = new ActivityScenarioRule<>(LoginActivity.class);


    @Test
    public void testPasswordHintVisibility(){
        // check hint visibility
        onView(withId(R.id.password)).check(matches(withHint("Password")));
        // enter name
        onView(withId(R.id.password)).perform(typeText("myPassword1"),closeSoftKeyboard());
        onView(withId(R.id.password)).check(matches(withText("myPassword1")));
    }


    @Test
    public void isForgotPasswordButton(){
        //onView(withText("UI")).perform(pressBack());
        onView(withText("Forgot Password")).check(matches(isDisplayed()));
    }

    @Test
    public void isLoginButton(){
        onView(withText("Sign In"));
    }

    @Test
    public void registerButtonTest(){
        //onView(withText("System UI isn't responding")).inRoot(isDialog()).check(matches(isDisplayed())).perform(pressBack());
        onView(withId(R.id.registerButton))
                .perform(click());
    }

    @Test
    public void testGoToMainScreen() {
        onView(withId(R.id.email))
                .perform(typeText("example@email.com"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.registerButton)).perform(click());
    }


    @Test
    public void testHintVisibility(){
        onView(withId(R.id.email)).perform(typeText("example@email.com"),closeSoftKeyboard());
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.email)).check(matches(withHint("E-mail")));
    }
    }
