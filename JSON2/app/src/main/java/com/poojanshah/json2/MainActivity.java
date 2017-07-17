package com.poojanshah.json2;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.poojanshah.json2.model.Flower;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {
    private RequestInterface requestInterface;
    private SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestInterface = ConnectSerice.getConnecction();

        BooksViewModel booksViewModel = new BooksViewModel(new RequestInterface() {
            @Override
            public Observable<List<Flower>> getFlowerList() {
                return requestInterface.getFlowerList();
            }
        }, AndroidSchedulers.mainThread());
//        ReactiveNetwork.observeInternetConnectivity()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Boolean>() {
//                    @Override public void accept(Boolean isConnectedToInternet) {
//                        // do something with isConnectedToInternet value
//                        if(isConnectedToInternet){
//                            requestInterface.getFlowerList()
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribeOn(Schedulers.newThread())
//                                    .subscribe(MainActivity.this:: onSuccess, MainActivity.this:: OnError);
//                            Toast.makeText(MainActivity.this,"Network is Available",Toast.LENGTH_LONG).show();
//                        } else{
//                            Toast.makeText(MainActivity.this,"Network is Available",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });


        requestInterface.getFlowerList().subscribe(new Observer<List<Flower>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull List<Flower> flowers) {
                for(Flower f: flowers){
                    Log.i("F.GetName",f.getName());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

}
