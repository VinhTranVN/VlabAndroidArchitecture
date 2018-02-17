package vlab.android.common.util;

import android.util.Log;

public class LogUtils {

    private static LogUtils sInstance = null;
    private static boolean isDebuggable = false;

    private LogUtils() {
        // Do nothing
    }

    public static LogUtils init(boolean isDebugMode) {
        if(sInstance == null){
            sInstance = new LogUtils();
        }
        isDebuggable = isDebugMode;
        return sInstance;
    }

    public static void println(String message) {
        if (isDebuggable){
            System.out.println(message);
        }
    }

    // log verbal
    public static void v(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.v(tag, msg);
        }
    }

    // log debug
    public static void d(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.d(tag, msg);
        }
    }

    // log info
    public static void i(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.i(tag, msg);
        }
    }

    // log error
    public static void e(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.e(tag, msg);
        }
    }

    public static void printStackTrace(Exception e) {
        if (e != null && isDebuggable) {
            e.printStackTrace();
        }
    }

    /*public static void showJsonObjectLog(String tag, Object obj) {
        if (isDebuggable) {
            Gson gson = new Gson();
            String json = gson.toJson(obj);
            Log.i(tag, json);
        }
    }*/

    /*public static void sendCrashlytics(Exception e) {
        try {
            printStackTrace(e);
            Crashlytics.logException(e);
        } catch (Exception e1) {}
    }*/
}
