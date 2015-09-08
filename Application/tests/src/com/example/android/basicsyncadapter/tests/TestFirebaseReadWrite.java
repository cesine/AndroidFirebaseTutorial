package com.example.android.basicsyncadapter.tests;

import android.annotation.TargetApi;
import android.test.ActivityInstrumentationTestCase2;

import com.example.android.basicsyncadapter.BuildConfig;
import com.example.android.basicsyncadapter.EntryListActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class TestFirebaseReadWrite extends ActivityInstrumentationTestCase2<EntryListActivity> {

    private EntryListActivity mTestActivity;

    @TargetApi(8)
    public TestFirebaseReadWrite() {
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

    public void testFirebaseWrite() {
        Firebase myFirebaseRef = new Firebase(BuildConfig.FIREBASE_URL);

        final String valueToWrite = "one";
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                assertEquals(snapshot.getValue(), "one");
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        myFirebaseRef.child("message").setValue(valueToWrite);
    }

}
