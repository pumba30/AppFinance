package com.pundroid.appfinance.objects;

import android.app.Application;

import com.pundroid.appfinance.databases.DbAdapter;

/**
 * Created by pumba30 on 12.08.2015.
 */
public class AppGlobalContext extends Application {

    private static DbAdapter dbAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        dbAdapter = new DbAdapter(this);
    }

    public  static DbAdapter getInstanceDbAdapter(){
        return dbAdapter;
    }
}
