LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := fib
LOCAL_SRC_FILES := fib.cpp

include $(BUILD_SHARED_LIBRARY)
