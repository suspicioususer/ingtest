package com.project.falconic_solutions.ingtest.test;


import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.project.falconic_solutions.ingtest.R;
import com.project.falconic_solutions.ingtest.view.ui.MainActivity;
import com.project.falconic_solutions.ingtest.view.ui.ProjectListFragment;

import org.junit.Rule;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

@CucumberOptions(features = "features")
public class MainActivitySteps {

    @Rule
    public ActivityTestRule<MainActivity> activityMainTestRule = new ActivityTestRule<>(MainActivity.class);

    private Activity activity;

    @Before
    public void setup() {
        activityMainTestRule.launchActivity(null);
        activity = activityMainTestRule.getActivity();
    }

    @After
    public void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activityMainTestRule.getActivity().finish();
    }


    @Given("^create main activity")
    public void create_main_activity() {

        if (activity == null) {
            setup();
        }

        assertNotNull(activity);
    }

    @When("^input username (\\S+)$")
    public void input_username(final String username) {
        onView(withId(R.id.etUsername)).perform(typeText(username));
        ViewActions.closeSoftKeyboard();
    }

    @When("^press submit button$")
    public void press_submit_button() {
        onView(withId(R.id.btSubmit)).perform(click());
    }

    @Then("^log$")
    public void log() {
       System.out.println("MainActivitySteps log");
    }

}
