package com.marakana.android.fibonacciservice;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.marakana.android.fibonaccicommon.FibonacciRequest;
import com.marakana.android.fibonaccicommon.FibonacciResponse;
import com.marakana.android.fibonaccicommon.IFibonacciListener;
import com.marakana.android.fibonaccicommon.IFibonacciService;
import com.marakana.android.fibonaccinative.FibLib;

public class IFibonacciServiceImpl extends IFibonacciService.Stub {
	private static final String TAG = "IFibonacciServiceImpl";

	private Context context;

	public IFibonacciServiceImpl(Context context) {
		this.context = context;
	}

	private void checkPermission() {
		if (context
				.checkCallingOrSelfPermission("com.marakana.android.FIBONACCI_SLOW_SERVICE") != PackageManager.PERMISSION_GRANTED) {
			throw new SecurityException("Not allowed to use slow algorithm");
		}
	}

	public long fibJI(long n) {
		Log.d(TAG, String.format("fibJI(%d)", n));
		return FibLib.fibJI(n);
	}

	public long fibJR(long n) {
		checkPermission();

		Log.d(TAG, String.format("fibJR(%d)", n));
		return FibLib.fibJR(n);
	}

	public long fibNI(long n) {
		Log.d(TAG, String.format("fibNI(%d)", n));
		return FibLib.fibNI(n);
	}

	public long fibNR(long n) {
		checkPermission();

		Log.d(TAG, String.format("fibNR(%d)", n));
		return FibLib.fibNR(n);
	}

	public FibonacciResponse fib(FibonacciRequest request) {
		Log.d(TAG,
				String.format("fib(%d, %s)", request.getN(), request.getType()));
		long timeInMillis = SystemClock.uptimeMillis();
		long result;
		switch (request.getType()) {
		case ITERATIVE_JAVA:
			result = this.fibJI(request.getN());
			break;
		case RECURSIVE_JAVA:
			result = this.fibJR(request.getN());
			break;
		case ITERATIVE_NATIVE:
			result = this.fibNI(request.getN());
			break;
		case RECURSIVE_NATIVE:
			result = this.fibNR(request.getN());
			break;
		default:
			return null;
		}
		timeInMillis = SystemClock.uptimeMillis() - timeInMillis;
		return new FibonacciResponse(result, timeInMillis);
	}

	@Override
	public void asyncFib(final FibonacciRequest request,
			final IFibonacciListener listener) throws RemoteException {
		new Thread() {
			public void run() {
				FibonacciResponse response = IFibonacciServiceImpl.this
						.fib(request);
				try {
					listener.onResponse(response);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
}
