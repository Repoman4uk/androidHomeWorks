package com.itis.androidlab.retrofit.utils;

import com.itis.androidlab.retrofit.BuildConfig;
import com.itis.androidlab.retrofit.Config;

public final class AndroidUtils {

    private AndroidUtils() {
    }

    public static String getRestEndpoint() {
        return BuildConfig.DEBUG ? Config.WEATHER_ENDPOINT_DEBUG : Config.WEATHER_ENDPOINT_RELEASE;
    }
}
