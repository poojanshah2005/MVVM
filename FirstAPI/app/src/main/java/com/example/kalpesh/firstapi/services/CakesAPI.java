package com.example.kalpesh.firstapi.services;

import com.example.kalpesh.firstapi.model.CakeListModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by kalpesh on 11/10/2016.
 */

public interface CakesAPI {

    @GET(Constants.RELATIVE_CAKE_URL)
    Observable<List<CakeListModel>> getCakeList();

}
