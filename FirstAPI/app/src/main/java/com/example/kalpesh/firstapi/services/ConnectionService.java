package com.example.kalpesh.firstapi.services;

import android.util.Log;

import com.example.kalpesh.firstapi.model.CakeListModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by kalpesh on 11/10/2016.
 */

public class ConnectionService implements Interactor{

    @Override
    public Observable<List<CakeListModel>> getCakes() {
        Log.i("Interactor","ConnectionService");
        return getConnectionService().getCakeList();
    }

    public ConnectionService(){
        getConnectionService();
    }
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;

    public static CakesAPI getConnectionService(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_CAKE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(CakesAPI.class);
    }


}
