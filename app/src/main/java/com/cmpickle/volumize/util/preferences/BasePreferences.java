package com.cmpickle.volumize.util.preferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/14/2017.
 */

public abstract class BasePreferences<T extends BasePreferences> {

    private final Object editorMutex = new Object();
    protected SharedPreferences sharedPreferences;
    private volatile SharedPreferences.Editor editor;
//    private EncryptUtil encryptUtil;

    public BasePreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void store() {
        if(editor != null) {
            editor.apply();
            editor = null;
        } else {
            throw new IllegalStateException("can't call store without calling first an editing method");
        }
    }

    @SuppressWarnings("unchecked")
    public T remove(String key) {
        editor().remove(key);
        return (T) this;
    }

    public String getString(String key) {
        return  sharedPreferences.getString(key, null);
    }

    public void setString(String key, String value) {
        withString(key, value).store();
    }

    @SuppressWarnings("unchecked")
    public T withString(String key, String value) {
        editor().putString(key, value);
        return (T) this;
    }

//    public String getSecret(String key) {
//        String encrypted = getString(key);
//        if(encrypted == null){
//            return null;
//        }
//        return encryptUtil.decrypt(encrypted);
//    }

//    public T withSecret(String key, String value) {
//        return withString(key, encryptUtil.encrypt(value));
//    }

    public Long getLong(String key) {
        if(!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getLong(key, 0);
    }

    public void setLong(String key, Long value) {
        withLong(key, value).store();
    }

    @SuppressWarnings("unchecked")
    public T withLong(String key, Long value) {
        if(value == null) {
            editor().remove(key);
        } else {
            editor().putLong(key, value);
        }
        return (T) this;
    }

    public Integer getInteger(String key) {
        if(!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getInt(key, 0);
    }

    public void setIngeger(String key, Integer value) {
        withInteger(key, value).store();
    }

    @SuppressWarnings("unchecked")
    public T withInteger(String key, Integer value) {
        if(value == null) {
            editor().remove(key);
        } else {
            editor().putInt(key, value);
        }
        return (T) this;
    }

    public Boolean getBoolean(String key) {
        if(!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getBoolean(key, false);
    }

    public void setBoolean(String key, Boolean value) {
        withBoolean(key, value).store();
    }

    @SuppressWarnings("unchecked")
    public T withBoolean(String key, Boolean value) {
        if(value == null) {
            editor().remove(key);
        } else {
            editor().putBoolean(key, value);
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T withFloat(String key, Float value) {
        if(value == null) {
            editor().remove(key);
        } else {
            editor().putFloat(key, value);
        }
        return (T) this;
    }

    public Float getFloat(String key) {
        if(!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getFloat(key, 0);
    }

    public void setFloat(String key, Float value) {
        withFloat(key, value).store();
    }

    @SuppressWarnings("unchecked")
    public T clearAllSharedPreferences() {
        editor().clear();
        return (T) this;
    }

    @SuppressLint("CommitPrefEdits")
    private SharedPreferences.Editor editor() {
        if(editor == null) {
            synchronized (editorMutex) {
                editor = sharedPreferences.edit();
            }
        }
        return editor;
    }
}
