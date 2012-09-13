package com.marakana.android.fibonaccicommon;

import com.marakana.android.fibonaccicommon.FibonacciResponse;

oneway interface IFibonacciListener {
	void onResponse(in FibonacciResponse response );
}