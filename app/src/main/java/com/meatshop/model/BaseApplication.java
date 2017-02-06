package com.meatshop.model;

import android.app.Application;

public class BaseApplication extends Application {
    private static String locale;

    public static String getLocale() { return locale;}

    public static void setLocale(String loc) {
        locale = loc;
    }
}
