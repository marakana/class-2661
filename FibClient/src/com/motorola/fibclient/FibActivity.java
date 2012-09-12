package com.motorola.fibclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.motorola.fibcommon.IFibService;

public class FibActivity extends Activity {
	private static final Intent FIB_SERVICE_INTENT = new Intent(
			"com.motorola.fibcommon.IFibService");
	private FibServiceConnection FIB_CONNECTION = new FibServiceConnection();
	private EditText input;
	private TextView output;
	private IFibService fibService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fib);

		input = (EditText) findViewById(R.id.input);
		output = (TextView) findViewById(R.id.output);
		
		bindService(FIB_SERVICE_INTENT, FIB_CONNECTION,
				Context.BIND_AUTO_CREATE);
	}

	private class FibServiceConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			fibService = IFibService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			fibService = null;
		}
	}

	public void onClickGo(View v) throws RemoteException {
		if (fibService == null)
			return;

		long n = Long.parseLong(input.getText().toString());

		// Java
		long start = System.nanoTime();
		long resultJ = fibService.fibJ(n);
		long timeJ = System.nanoTime() - start;
		output.append(String
				.format("\nfibJ(%d)=%d (%d ns)", n, resultJ, timeJ));

		// Native
		start = System.nanoTime();
		long resultN = fibService.fibN(n);
		long timeN = System.nanoTime() - start;
		output.append(String
				.format("\nfibN(%d)=%d (%d ns)", n, resultN, timeN));
	}
}
