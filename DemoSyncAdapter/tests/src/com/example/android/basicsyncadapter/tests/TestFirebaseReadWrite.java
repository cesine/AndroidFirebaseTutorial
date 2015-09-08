package com.example.android.basicsyncadapter.tests;

import android.annotation.TargetApi;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

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

        final String valueToWrite = "I can't change this because I'm not authenticated";
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            int dataChangeCount = 0;

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d(TestFirebaseReadWrite.class.getSimpleName(), "snapshot.getValue() " + snapshot.getValue());
                if ("one".equals(snapshot.getValue())) {
                    // value was updated before database security rules were added
                    assertEquals("one", snapshot.getValue());
                } else {
                    if (dataChangeCount == 0) {
                        // value seems to have been updated
                        assertEquals("I can't change this because I'm not authenticated", snapshot.getValue());
                    } else {
                        // value was not actually updated because its blocked by security rules
                        assertNull(snapshot.getValue());
                    }
                }
                dataChangeCount++;
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        myFirebaseRef.child("message").setValue(valueToWrite, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    Log.e(TestFirebaseReadWrite.class.getSimpleName(), "Data could not be saved. " + firebaseError.getMessage());
                } else {
                    Log.d(TestFirebaseReadWrite.class.getSimpleName(), "Data saved successfully.");
                }
            }
        });
    }
}
