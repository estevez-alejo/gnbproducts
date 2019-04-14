package com.alejoestevez.gnbproducts.utilities;

import android.app.Application;

public class AppApplication extends Application {

    private static AppApplication mInstance;

    public static synchronized AppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}