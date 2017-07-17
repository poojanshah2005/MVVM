package com.example.kalpesh.firstapi.CakeList;

import android.util.Log;

import com.example.kalpesh.firstapi.model.CakeListModel;
import com.example.kalpesh.firstapi.services.Interactor;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kalpesh on 16/04/2017.
 */

public class Presenter_CakesList implements ICakeListContract.IPresenter {


    private ICakeListContract.IView view;
    private Interactor interactor;
    private CompositeSubscription compositeSubscription= new CompositeSubscription();

    @Override
    public void bind(ICakeListContract.IView view) {
        this.view=view;
        if(compositeSubscription.isUnsubscribed())
        compositeSubscription.add(callService());

    }

    @Override
    public void unBind() {
        view=null;
        if(compositeSubscription!=null)
        compositeSubscription.unsubscribe();
    }

    /**
     * Instead of exposing the presenter we only expose the Interactor
     * @param interactor
     */
    public Presenter_CakesList(Interactor interactor) {
        this.interactor= interactor;
    }

    @Override
    public void getCakeList() {

        callService();
    }


    public Subscription callService(){
//        if(NetworkCheck.isNetworkAvailable()) {
           return(interactor.getCakes()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<CakeListModel>>() {
                        @Override
                        public void onCompleted() {
                        //    mSwipeRefreshLayout.setRefreshing(false);
                            Log.i("Interactor","onCompleted");

                        }

                        @Override
                        public void onError(Throwable e) {
                            //  pDialog.dismiss();
                          //  mSwipeRefreshLayout.setRefreshing(false);
                            view.dismissProgressDialog();

                        }

                        @Override
                        public void onNext(List<CakeListModel> cakeListModels) {
                            view.showCakesList(cakeListModels);
                            view.dismissProgressDialog();
/**
 * Connect adapter
 */
// Display data ion adapter
                            // Clear your Realm
                            // Fill with new data
                          //  mRecyclerView.setAdapter(new CakesAdapter(cakeListModels, R.layout.card_row, getApplicationContext()));
                            //  pDialog.dismiss();


                        }
                    }));
        }
//        else{
//
//            //Read it from realm
//            //  mRecyclerView.setAdapter(new CakesAdapter(cakeListModels, R.layout.card_row, getApplicationContext()));
//
//
//            mSwipeRefreshLayout.setRefreshing(false);
//
//            //Toast.makeText(getApplicationContext(),"No Network Available", Toast.LENGTH_LONG).show();
//            alertDialog= new AlertDialog.Builder(this)
//                    .setMessage("No Network")
//                    .setTitle(" pLEASE CONNECT YOURSELF , Your viewing offline data")
//
//                    .setPositiveButton("ok",new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            alertDialog.dismiss();
//                            finish();
//                        }
//                    })
//                    .show();
//
//
//        }
    }

