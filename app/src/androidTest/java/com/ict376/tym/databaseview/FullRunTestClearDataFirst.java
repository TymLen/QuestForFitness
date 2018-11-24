package com.ict376.tym.databaseview;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
//Purpose: UI Automated test REQUIRES NEW DATABASE
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FullRunTestClearDataFirst {

    @Rule
    public ActivityTestRule<MainMenuHost> mActivityTestRule = new ActivityTestRule<>(MainMenuHost.class);

    @Test
    public void fullRunTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.CheckinBut), withText("Check In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withText("Check In"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Check In")));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText2.perform(scrollTo(), replaceText("11"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.SubmitBut), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout.perform(click());

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6));
        appCompatEditText3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText4 = onView(
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6));
        appCompatEditText4.perform(scrollTo(), replaceText("03-11-2019"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.debugDate), withText("03-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.debugDate), withText("03-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.debugDate), withText("03-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText7.perform(scrollTo(), replaceText("04-11-2019"));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.debugDate), withText("04-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.CheckinBut), withText("Check In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText9.perform(scrollTo(), click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText10.perform(scrollTo(), replaceText("22"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.SubmitBut), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton4.perform(scrollTo(), click());

        DataInteraction linearLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        linearLayout3.perform(click());

        DataInteraction linearLayout4 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout4.perform(click());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.debugDate), withText("04-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText11.perform(scrollTo(), click());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.debugDate), withText("04-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText12.perform(scrollTo(), replaceText("05-11-2019"));

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.debugDate), withText("05-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText13.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.debugDate), withText("05-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText14.perform(pressImeActionButton());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.CheckinBut), withText("Check In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText15.perform(scrollTo(), click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText16.perform(scrollTo(), replaceText("33"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.EnterWeight), withText("33"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText17.perform(pressImeActionButton());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.SubmitBut), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton6.perform(scrollTo(), click());

        DataInteraction linearLayout5 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(2);
        linearLayout5.perform(click());

        DataInteraction linearLayout6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout6.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("Main Menu"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Main Menu")));

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.debugDate), withText("05-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText18.perform(scrollTo(), click());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.debugDate), withText("05-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText19.perform(scrollTo(), replaceText("06-11-2019"));

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.debugDate), withText("06-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText20.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.debugDate), withText("06-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText21.perform(pressImeActionButton());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.CheckinBut), withText("Check In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText22.perform(scrollTo(), click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText23.perform(scrollTo(), replaceText("44"), closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.EnterWeight), withText("44"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText24.perform(pressImeActionButton());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.SubmitBut), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.listText), withText("Use Upper Body"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.OptionList),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Use Upper Body")));

        DataInteraction linearLayout7 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout7.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.listText), withText("Record Adventure"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.OptionList),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Record Adventure")));

        DataInteraction linearLayout8 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout8.perform(click());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.debugDate), withText("06-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText25.perform(scrollTo(), click());

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.debugDate), withText("06-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText26.perform(scrollTo(), replaceText("07-11-2019"));

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.debugDate), withText("07-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText27.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.CheckinBut), withText("Check In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText28.perform(scrollTo(), click());

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText29.perform(scrollTo(), replaceText("55"), closeSoftKeyboard());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.SubmitBut), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton10.perform(scrollTo(), click());

        DataInteraction linearLayout9 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        linearLayout9.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.listText), withText("Record Adventure"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.OptionList),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Record Adventure")));

        DataInteraction linearLayout10 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout10.perform(click());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.debugDate), withText("07-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText30.perform(scrollTo(), click());

        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.debugDate), withText("07-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText31.perform(scrollTo(), replaceText("08-11-2019"));

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.debugDate), withText("08-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText32.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.CheckinBut), withText("Check In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction appCompatEditText33 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText33.perform(scrollTo(), click());

        ViewInteraction appCompatEditText34 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText34.perform(scrollTo(), replaceText("66"), closeSoftKeyboard());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.SubmitBut), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton12.perform(scrollTo(), click());

        DataInteraction linearLayout11 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(2);
        linearLayout11.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.listText), withText("Record Adventure"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.OptionList),
                                        0),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Record Adventure")));

        DataInteraction linearLayout12 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout12.perform(click());

        ViewInteraction appCompatEditText35 = onView(
                allOf(withId(R.id.debugDate), withText("08-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText35.perform(scrollTo(), click());

        ViewInteraction appCompatEditText36 = onView(
                allOf(withId(R.id.debugDate), withText("08-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText36.perform(scrollTo(), replaceText("09-11-2019"));

        ViewInteraction appCompatEditText37 = onView(
                allOf(withId(R.id.debugDate), withText("09-11-2019"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText37.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.CheckinBut), withText("Check In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction appCompatEditText38 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText38.perform(scrollTo(), click());

        ViewInteraction appCompatEditText39 = onView(
                allOf(withId(R.id.EnterWeight),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatEditText39.perform(scrollTo(), replaceText("77"), closeSoftKeyboard());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.SubmitBut), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton14.perform(scrollTo(), click());

        DataInteraction linearLayout13 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout13.perform(click());

        DataInteraction linearLayout14 = onData(anything())
                .inAdapterView(allOf(withId(R.id.OptionList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout14.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.LevelUpBut), withText("Level Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton15.perform(scrollTo(), click());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.GratzText), withText("Hero Promotion"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("Hero Promotion")));

        ViewInteraction button = onView(
                allOf(withId(R.id.PhotoButton), withText("Take Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        8),
                                0)));
        button.perform(scrollTo(), click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.PhotoTitle), withText("Hero Portrait"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("Hero Portrait")));

        pressBack();

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.GratzText), withText("Hero Promotion"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("Hero Promotion")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.skipPhotoBut), withText("Skip Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        8),
                                1)));
        button2.perform(scrollTo(), click());

        ViewInteraction textView10 = onView(
                allOf(withText("Main Menu"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView10.check(matches(withText("Main Menu")));

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.LogbookBut), withText("Logbook"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton16.perform(scrollTo(), click());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.listText), withText("Level 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.LevelList),
                                        0),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText("Level 1")));

        DataInteraction linearLayout15 = onData(anything())
                .inAdapterView(allOf(withId(R.id.LevelList),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        linearLayout15.perform(click());

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.DLevel), withText("Fitness Level: 1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView12.check(matches(withText("Fitness Level: 1")));

        pressBack();

        ViewInteraction textView13 = onView(
                allOf(withText("Logbook"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView13.check(matches(withText("Logbook")));

        pressBack();

        ViewInteraction textView14 = onView(
                allOf(withText("Main Menu"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView14.check(matches(withText("Main Menu")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
