package com.motorola.lognative;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LogActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_log, menu);
        return true;
    }
}
