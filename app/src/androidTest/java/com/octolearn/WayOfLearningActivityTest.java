package com.octolearn;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



public class WayOfLearningActivityTest {

    @Rule
    public final ActivityScenarioRule<WayOfLearningActivity> rule = new ActivityScenarioRule<>(WayOfLearningActivity.class);

    @Test
    public void clickFlashcardsButton_opensFlashcards() {
        onView(withId(R.id.flashcards))
                .perform(click());
        onView(withId(R.id.displayFlashcards))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickTestKnowledgeButton_opensTestK() {
        onView(withId(R.id.testKnowledge))
                .perform(click());
        onView(withId(R.id.testKnowledgeA))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickTestWordsList_opensList() {
        onView(withId(R.id.wordsListView))
                .perform(click());
        onView(withId(R.id.wordsList))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickTestDeleteButton_opensTestDialog() {
        onView(withId(R.id.deleteCatalog))
                .perform(click());
        onView(withText("Delete catalog"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickTestDialogNo_doesNothing() {
        onView(withId(R.id.deleteCatalog))
                .perform(click());
        onView(withText("NO"))
                .perform(click());
        onView(withId(R.id.insideCatalog))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickTestDialogYes_opensMainA() {
        onView(withId(R.id.deleteCatalog))
                .perform(click());
        onView(withText("YES"))
                .perform(click());
        onView(withId(R.id.drawer_layout))
                .check(matches(isDisplayed()));
    }
}
