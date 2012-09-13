package com.marakana.android.fibonacciclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.marakana.android.fibonaccicommon.FibonacciRequest;
import com.marakana.android.fibonaccicommon.FibonacciResponse;
import com.marakana.android.fibonaccicommon.IFibonacciListener;
import com.marakana.android.fibonaccicommon.IFibonacciService;

public class FibonacciActivity extends Activity implements OnClickListener,
		ServiceConnection {

	private static final String TAG = "FibonacciActivity";

	private EditText input; // our input n

	private Button button; // trigger for fibonacci calcualtion

	private RadioGroup type; // fibonacci implementation type

	private TextView output; // destination for fibonacci result

	private IFibonacciService service; // reference to our service

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		// connect to our UI elements
		this.input = (EditText) super.findViewById(R.id.input);
		this.button = (Button) super.findViewById(R.id.button);
		this.type = (RadioGroup) super.findViewById(R.id.type);
		this.output = (TextView) super.findViewById(R.id.output);
		// request button click call-backs via onClick(View) method
		this.button.setOnClickListener(this);
		// the button will be enabled once we connect to the service
		this.button.setEnabled(false);
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume()'ed");
		super.onResume();
		// Bind to our FibonacciService service, by looking it up by its name
		// and passing ourselves as the ServiceConnection object
		// We'll get the actual IFibonacciService via a callback to
		// onServiceConnected() below
		if (!super.bindService(new Intent(IFibonacciService.class.getName()),
				this, BIND_AUTO_CREATE)) {
			Log.w(TAG, "Failed to bind to service");
		}
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause()'ed");
		super.onPause();
		// No need to keep the service bound (and alive) any longer than
		// necessary
		super.unbindService(this);
	}

	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.d(TAG, "onServiceConnected()'ed to " + name);
		// finally we can get to our IFibonacciService
		this.service = IFibonacciService.Stub.asInterface(service);
		// enable the button, because the IFibonacciService is initialized
		this.button.setEnabled(true);
	}

	public void onServiceDisconnected(ComponentName name) {
		Log.d(TAG, "onServiceDisconnected()'ed to " + name);
		// our IFibonacciService service is no longer connected
		this.service = null;
		// disabled the button, since we cannot use IFibonacciService
		this.button.setEnabled(false);
	}

	// handle button clicks
	public void onClick(View view) {
		// parse n from input (or report errors)
		final long n;
		String s = this.input.getText().toString();
		if (TextUtils.isEmpty(s)) {
			return;
		}
		try {
			n = Long.parseLong(s);
		} catch (NumberFormatException e) {
			this.input.setError(super.getText(R.string.input_error));
			return;
		}

		// build the request object
		final FibonacciRequest.Type type;
		switch (FibonacciActivity.this.type.getCheckedRadioButtonId()) {
		case R.id.type_fib_jr:
			type = FibonacciRequest.Type.RECURSIVE_JAVA;
			break;
		case R.id.type_fib_ji:
			type = FibonacciRequest.Type.ITERATIVE_JAVA;
			break;
		case R.id.type_fib_nr:
			type = FibonacciRequest.Type.RECURSIVE_NATIVE;
			break;
		case R.id.type_fib_ni:
			type = FibonacciRequest.Type.ITERATIVE_NATIVE;
			break;
		default:
			return;
		}
		final FibonacciRequest request = new FibonacciRequest(n, type);

		try {
			// Asynchronous call
			this.service.asyncFib(request, fibonacciListener);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	private final IFibonacciListener fibonacciListener = new IFibonacciListener.Stub() {
		
		@Override
		public void onResponse(FibonacciResponse response) throws RemoteException {
			String result = String.format(
					"fibonacci()=%d\nin %d ms\n",
					response.getResult(), response.getTimeInMillis(),
					response.getTimeInMillis());			
			FibonacciActivity.this.output.setText(result);
		}
	};
}
