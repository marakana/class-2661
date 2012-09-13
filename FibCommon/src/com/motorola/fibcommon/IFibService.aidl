package com.motorola.fibcommon;

import com.motorola.fibcommon.Request;
import com.motorola.fibcommon.Response;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	Response fib(in Request request);
}