package com.motorola.fibservice;

import android.os.RemoteException;
import com.motorola.fibcommon.IFibService;
import com.motorola.fibcommon.Request;
import com.motorola.fibcommon.Response;

public class IFibServiceImpl extends IFibService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

	@Override
	public Response fib(Request request) throws RemoteException {
		long start = System.nanoTime();
		long result;
		
		switch( request.getAlgorithm() ) {
		case Request.ALGORITHM_NATIVE:
			result = FibLib.fibN( request.getN() );
			break;
		case Request.ALGORITHM_JAVA:
			result = FibLib.fibJ( request.getN() );
			break;
		default:
			result = -1;
		}
		
		long time = System.nanoTime()-start;
		
		return new Response(result, time);
	}

}
