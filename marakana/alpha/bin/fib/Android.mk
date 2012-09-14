LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := fib.c
LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../include/
LOCAL_SHARED_LIBRARIES := libfib
LOCAL_MODULE := fib
include $(BUILD_EXECUTABLE)

