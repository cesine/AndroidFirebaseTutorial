package com.example.android.basicsyncadapter.tests;

import android.annotation.TargetApi;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.example.android.basicsyncadapter.BuildConfig;
import com.example.android.basicsyncadapter.EntryListActivity;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class TestFirebaseRegisterUsers extends ActivityInstrumentationTestCase2<EntryListActivity> {

    private EntryListActivity mTestActivity;

    @TargetApi(8)
    public TestFirebaseRegisterUsers() {
        super(EntryListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mTestActivity = getActivity();
        mTestActivity.getSupportFragmentManager().getFragments().get(0);
    }

    /**
     * Test if the test fixture has been set up correctly.
     */
    public void testPreconditions() {
        //Try to add a message to add context to your assertions. These messages will be shown if
        //a tests fails and make it easy to understand why a test failed
        assertNotNull("mTestActivity is null", mTestActivity);
    }

    public void testFirebaseRegisterUserWithEmail() {
        Firebase myFirebaseRef = new Firebase(BuildConfig.FIREBASE_URL);
        assertNotNull(myFirebaseRef);

        myFirebaseRef.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d(TestFirebaseRegisterUsers.class.getSimpleName(), "Successfully created user account with uid: " + result.get("uid"));
                assertNotNull(result);
                assertNotNull(result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
                Log.e(TestFirebaseRegisterUsers.class.getSimpleName(), firebaseError.getDetails());
                Log.e(TestFirebaseRegisterUsers.class.getSimpleName(), firebaseError.getMessage());
                assertTrue(firebaseError.getMessage().contains("The specified email address is already in use."));
            }
        });

    }

    public void testFirebaseAuthUserWithPassword() {
        Firebase myFirebaseRef = new Firebase(BuildConfig.FIREBASE_URL);
        myFirebaseRef.authWithPassword("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.d(TestFirebaseRegisterUsers.class.getSimpleName(), "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                assertNotNull(authData.getUid());
                assertEquals("password", authData.getProvider());
                assertEquals(36, authData.getUid().length());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e(TestFirebaseRegisterUsers.class.getSimpleName(), firebaseError.getDetails());
                Log.e(TestFirebaseRegisterUsers.class.getSimpleName(), firebaseError.getMessage());
                assertEquals("User should have been logged in.", false);
            }
        });
    }

    public void testFirebaseAuthUserWithWrongPassword() {
        Firebase myFirebaseRef = new Firebase(BuildConfig.FIREBASE_URL);
        myFirebaseRef.authWithPassword("bobtony@firebase.com", "wrongpassword", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.d(TestFirebaseRegisterUsers.class.getSimpleName(), "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                assertEquals("User should not have been logged in.", false);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e(TestFirebaseRegisterUsers.class.getSimpleName(), "firebaseError.getCode() " + firebaseError.getCode());
                Log.e(TestFirebaseRegisterUsers.class.getSimpleName(), "firebaseError.getMessage() " + firebaseError.getMessage());
                assertEquals(-16, firebaseError.getCode());
                assertTrue(firebaseError.getMessage().contains("The specified password is incorrect"));
            }
        });
    }

}
