package com.motorola.fibservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FibService extends Service {
	private IFibServiceImpl serviceImpl=null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		serviceImpl = new IFibServiceImpl();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return serviceImpl;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		serviceImpl = null;
	}
}
