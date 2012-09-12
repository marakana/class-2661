package com.motorola.fibnative;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FibActivity extends Activity {
	EditText input;
	TextView output;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fib);
        
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
    }

    public void onClickGo(View v) {
    		long n = Long.parseLong( input.getText().toString() );
    		
    		long resultJ = FibLib.fibJ(n);
    		output.append( String.format("\nfibJ(%d)=%d", n, resultJ));
    		
    		long resultN = FibLib.fibN(n);
    		output.append( String.format("\nfibN(%d)=%d", n, resultN));
    }
}
