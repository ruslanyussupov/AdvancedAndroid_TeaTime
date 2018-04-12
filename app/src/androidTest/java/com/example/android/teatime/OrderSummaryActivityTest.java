/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.teatime;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

// COMPLETED (1) Add annotation to specify AndroidJUnitRunner class as the default test runner
@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {

    private static final String MESSAGE = "I just ordered a delicious tea from TeaTime. Next time you are craving a tea, check them out!";

    // COMPLETED (2) Add the rule that indicates we want to use Espresso-Intents APIs in functional UI tests
    @Rule
    public IntentsTestRule<OrderSummaryActivity> mIntentsTestRule =
            new IntentsTestRule<>(OrderSummaryActivity.class);

    // COMPLETED (3) Finish this method which runs before each test and will stub all external
    // intents so all external intents will be blocked

    @Before
    public void stubAllExternalIntents() {

        Intents.intending(IsNot.not(IntentMatchers.isInternal()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

    }


    // COMPLETED (4) Finish this method which verifies that the intent sent by clicking the send email
    // button matches the intent sent by the application

    @Test
    public void clickSendEmailButton_SendsEmail() {

        Espresso.onView(ViewMatchers.withId(R.id.send_email_button)).perform(ViewActions.click());

        Intents.intended(AllOf.allOf(
                IntentMatchers.hasAction(Intent.ACTION_SENDTO),
                IntentMatchers.hasExtra(Intent.EXTRA_TEXT, MESSAGE)));

    }
}