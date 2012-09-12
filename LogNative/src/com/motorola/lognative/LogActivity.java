package com.motorola.lognative;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogActivity extends Activity {
	EditText tag, msg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		tag = (EditText) findViewById(R.id.tag);
		msg = (EditText) findViewById(R.id.msg);
	}

	public void onClickLog(View v) {
		LogLib.log(LogLib.DEBUG, tag.getText().toString(), msg.getText()
				.toString());
	}
}
