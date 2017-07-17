package info.androidhive.retrofit.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by kalpesh on 14/06/2016.
 */
public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();


        MyApp.context = getApplicationContext();


    }

    public static Context getAppContext() {
        return MyApp.context;
    }


}


