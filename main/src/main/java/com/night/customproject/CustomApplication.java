package com.night.customproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by Night on 7/12/16.
 * Description:
 */
public class CustomApplication extends Application{

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
