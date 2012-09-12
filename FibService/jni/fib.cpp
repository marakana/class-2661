#include <jni.h>

namespace com_motorola_fibnative {

	// Plain C function
	jlong fib(jlong n) {
		if(n==0) return 0;
		if(n==1) return 1;
		return fib(n-1) + fib(n-2);
	}

	jlong fibI(jlong n) {
		jlong previous = -1;
		jlong result = 1;
		for (jlong i = 0; i <= n; i++) {
			jlong sum = result + previous;
			previous = result;
			result = sum;
		}
		return result;
	}

	// JNI wrapper functions
	jlong fibN(JNIEnv *env, jclass clazz, jlong n) {
		return fib(n);
	}
	jlong fibNI(JNIEnv *env, jclass clazz, jlong n) {
		return fibI(n);
	}

	static JNINativeMethod method_table[] = {
	     { "fibN", "(J)J", (void *) fibN },
	     { "fibNI", "(J)J", (void *) fibNI }
	};
}

using namespace com_motorola_fibnative;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    } else {
        jclass clazz = env->FindClass("com/motorola/fibservice/FibLib");
        if (clazz) {
            env->RegisterNatives(clazz, method_table, sizeof(method_table) / sizeof(method_table[0]));
            env->DeleteLocalRef(clazz);
            return JNI_VERSION_1_6;
        } else {
            return -1;
        }
    }
}
