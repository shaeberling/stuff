LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS) 

LOCAL_MODULE    := AndroidBinary

LOCAL_SRC_FILES := helloworld.cc
LOCAL_LDLIBS    := -llog

include $(BUILD_EXECUTABLE)

