package com.example.ft_hangout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PreferenceHelper {
    public static final String PREFERENCE = "APP_PREFERENCES";
    Date date;
    SimpleDateFormat formatter;

    @SuppressLint("SimpleDateFormat")
    public PreferenceHelper() {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public static void put(Context context, String key, Object object) {

        SharedPreferences sp = context.getSharedPreferences(PREFERENCE,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    public static Object get(Context context, String key, Object defaultObject) {

        SharedPreferences sp = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    public void dateDisplay(Context context){

        if (contains(context, "date")) {
            Object ret = get(context,"date", "");
            Toast.makeText(context, ret.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void dateSave(Context context){
        date = new Date();
        String datePref = formatter.format(date);
        put(context, "date", datePref);
    }

    public void newColor(Context context, String color){
        put(context, "color", color);
    }

    public void setColor(Context context, ActionBar aBar) {
        if (contains(context, "color")) {
            String color = get(context, "color", "").toString();
            Log.d("color", color);
            ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor(color));
            aBar.setBackgroundDrawable(cd);
        }
    }
}

//    Date d1 = sdf.parse(start_date);
//    Date d2 = sdf.parse(end_date);
//
//    // Calculate time difference
//    // in milliseconds
//    long difference_In_Time
//            = d2.getTime() - d1.getTime();
//
//    // Calculate time difference in
//    // seconds, minutes, hours, years,
//    // and days
//    long difference_In_Seconds
//            = (difference_In_Time
//            / 1000)
//            % 60;