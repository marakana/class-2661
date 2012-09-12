#include <jni.h>
#include <android/log.h>

namespace com_motorola_lognative {

	// JNI call
	void log(JNIEnv *env, jclass clazz, jint priority, jstring tag, jstring msg) {
		const char *c_tag = env->GetStringUTFChars(tag, 0);
		const char *c_msg = env->GetStringUTFChars(msg, 0);

		__android_log_write(priority, c_tag, c_msg);

		env->ReleaseStringUTFChars(tag, c_tag);
		env->ReleaseStringUTFChars(msg, c_msg);
	}

	// Method table
	static JNINativeMethod method_table[] = {
	     { "log", "(ILjava/lang/String;Ljava/lang/String;)V", (void *) log }
	};
}

using namespace com_motorola_lognative;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    } else {
        jclass clazz = env->FindClass("com/motorola/lognative/LogLib");
        if (clazz) {
            env->RegisterNatives(clazz, method_table, sizeof(method_table) / sizeof(method_table[0]));
            env->DeleteLocalRef(clazz);
            return JNI_VERSION_1_6;
        } else {
            return -1;
        }
    }
}
