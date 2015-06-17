package com.ben.secret;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ben on 2015/6/17.
 */
public class Config {

    public static final String APP_ID = "com.ben.secret";
    public static final String KEY_TOKEN = "token";
    public static final String CHARSET = "utf-8";

    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public static void cacheToken(Context context, String token){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }
}
