package com.motorola.fibnative;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FibActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fib);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fib, menu);
        return true;
    }
}
