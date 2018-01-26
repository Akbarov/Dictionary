package com.example.hp_pk.dictionary.manager;

import android.content.SharedPreferences;
import android.util.Base64;

import java.util.Set;

/**
 * @author akbar
 * @since 2017, July 17
 */

public class SharedPrefsHelper {

    private SharedPreferences prefs;

    public SharedPrefsHelper(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    private String encrypt(String input) {
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    private String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }

    public void put(String key, boolean value) {
        key = encrypt(key);
        prefs.edit().putBoolean(key, value).apply();
    }

    public void put(String key, String value) {
        key = encrypt(key);
        value = encrypt(value);
        prefs.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        key = encrypt(key);
        prefs.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        key = encrypt(key);
        prefs.edit().putFloat(key, value).apply();
    }

    public void put(String key, long value) {
        key = encrypt(key);
        prefs.edit().putLong(key, value).apply();
    }

    public void put(String key, Set<String> strings) {
        key = encrypt(key);
        prefs.edit().putStringSet(key, strings).apply();
    }

    public boolean get(String key, boolean defaultValue) {
        key = encrypt(key);
        return prefs.getBoolean(key, defaultValue);
    }

    public String get(String key, String defaultValue) {
        key = encrypt(key);
        defaultValue = encrypt(defaultValue);
        return decrypt(prefs.getString(key, defaultValue));
    }

    public int get(String key, int defaultValue) {
        key = encrypt(key);
        return prefs.getInt(key, defaultValue);
    }

    public float get(String key, float defaultValue) {
        key = encrypt(key);
        return prefs.getFloat(key, defaultValue);
    }

    public long get(String key, long defaultValue) {
        key = encrypt(key);
        return prefs.getLong(key, defaultValue);
    }

    public Set<String> get(String key, Set<String> defaultValues) {
        key = encrypt(key);
        return prefs.getStringSet(key, defaultValues);
    }

    public void deleteSavedData(String key) {
        key = encrypt(key);
        prefs.edit().remove(key).apply();
    }

    public void clearAllData() {
        prefs.edit().clear().apply();
    }
}
