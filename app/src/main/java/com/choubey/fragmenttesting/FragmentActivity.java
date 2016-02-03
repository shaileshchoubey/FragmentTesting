package com.choubey.fragmenttesting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class FragmentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fragment);
        }
        catch(Exception e)
        {
            Log.e(this.getClass().getSimpleName(), "Error loading template", e);
        }
    }
}
