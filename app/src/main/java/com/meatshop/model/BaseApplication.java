package com.meatshop.model;

import android.app.Application;

public class BaseApplication extends Application {
    private static String twitterBearer;
    private static String locale;

    public static String getTwitterBearer() {
        return twitterBearer;
    }

    public static void setTwitterBearer(String twitterBearer) {
        BaseApplication.twitterBearer = twitterBearer;
    }

    public static String getLocale() { return locale;}

    public static void setLocale(String loc) {
        locale = loc;
    }
}
