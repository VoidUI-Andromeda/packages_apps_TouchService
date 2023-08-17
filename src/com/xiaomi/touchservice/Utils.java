package com.xiaomi.touchservice;

import android.util.Log;

public class Utils {
    private static boolean DEBUG_ALL = false;

    public static void setDebugAll(boolean z) {
        DEBUG_ALL = z;
    }

    public static void logD(String str, String str2) {
        if (DEBUG_ALL) {
            Log.d(str, str2);
        }
    }

    public static void logI(String str, String str2) {
        Log.i(str, str2);
    }

    public static void logE(String str, String str2) {
        Log.e(str, str2);
    }
}
