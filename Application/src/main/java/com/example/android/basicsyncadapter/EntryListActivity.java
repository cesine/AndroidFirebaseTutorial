package com.example.android.basicsyncadapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.firebase.client.Firebase;

/**
 * Activity for holding EntryListFragment.
 */
public class EntryListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_entry_list);
    }
}
