package vlab.android.common.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    private static final String APPLICATION_PREFERENCES = "app_preferences";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;

    private PreferenceUtils() {
    }

    /**
     * check key exist or not
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(final Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        return prefs.contains(key);
    }

    public static void remove(final Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * Get boolean value in share preference
     * @param context
     * @param key
     * @param defaultValue
     * @return boolean
     */
    public static boolean getBoolean(final Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        return prefs.getBoolean(key, defaultValue);
    }

    /**
     * Set boolean value in share preference
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(final Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get string value in share preference
     * @param context
     * @param key
     * @param defaultValue
     * @return boolean
     */
    public static String getString(final Context context, String key, String defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        return prefs.getString(key, defaultValue);
    }

    /**
     * Set string value in share preference
     * @param context
     * @param key
     * @param value
     */
    public static void setString(final Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int getInt(final Context context, String key, int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        return prefs.getInt(key, defaultValue);
    }

    public static void setInt(final Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static long getLong(final Context context, String key, int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        return prefs.getLong(key, defaultValue);
    }

    public static void setLong(final Context context, String key, long value) {
        SharedPreferences prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, PREFERENCES_MODE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }
}
