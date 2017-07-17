package com.example.kalpesh.firstapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kalpesh.firstapi.CakeList.ICakeListContract;
import com.example.kalpesh.firstapi.CakeList.Presenter_CakesList;
import com.example.kalpesh.firstapi.adapter.CakesAdapter;
import com.example.kalpesh.firstapi.model.CakeListModel;
import com.example.kalpesh.firstapi.services.CakesAPI;
import com.example.kalpesh.firstapi.services.ConnectionService;
import com.example.kalpesh.firstapi.services.Interactor;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements ICakeListContract.IView{
        //implements ICakelistContract.IView {

    @Override
    public void shoProgressDisalog() {
        initialProgressDialog();
    }

    @Override
    public void showCakesList(List<CakeListModel> cakeListModels) {
        mRecyclerView.setAdapter(new CakesAdapter(cakeListModels, R.layout.card_row, getApplicationContext()));

    }

    @Override
    public void dismissProgressDialog() {
        if(pDialog!=null){
            pDialog.dismiss();
        }
    }

    private SwipeRefreshLayout mSwipeRefreshLayout;

    CakesAPI cake_api;
    RecyclerView mRecyclerView;
    ProgressDialog pDialog;
    AlertDialog alertDialog;
    Presenter_CakesList presenter_cakesList;
    CakesAdapter adapter;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private CakeListModel cakeListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);
        mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshCakes);
        initialRecyclerview();
        initializePresenter();

        Interactor interactor = new ConnectionService();
        Subscription subscription = interactor.getCakes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CakeListModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<CakeListModel> cakeListModels) {
                        for (CakeListModel c:cakeListModels){
                            Log.i("CakeListModel",c.getTitle());
                        }
                    }
                });



        presenter_cakesList.getCakeList();

       mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //callService();
                presenter_cakesList.getCakeList();

            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter_cakesList.unBind();
        super.onDestroy();
    }


    public void initializePresenter(){
        Interactor interactor= new ConnectionService();
        presenter_cakesList= new Presenter_CakesList(interactor);

        presenter_cakesList.bind(this);

    }
    /**
     * Taken care by the interactor
     */
    public void connectService(){
        cake_api= ConnectionService.getConnectionService();
    }

    public void initialRecyclerview(){
        mRecyclerView = (RecyclerView)findViewById(R.id.listRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    public void initialProgressDialog(){
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
    }






}
