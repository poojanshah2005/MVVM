package com.example.kalpesh.firstapi.services;

import com.example.kalpesh.firstapi.model.CakeListModel;

import java.util.List;

import rx.Observable;

/**
 * Created by kalpesh on 14/04/2017.
 */

/**
 * 1 Create an interface which has same observable but without Get
 * 2 Let the service implement Interactor interface (empty constructor to call getConnection())
 * 3 Create a contract where we define roles for both View and Presenter
 * 4 Let the MainActivity or Fragment implement the IView
 *   4.1 Implement the methods
 * 5 Create a new class and implement the presenter_interface
 *   5.1 Implement the methods
 *   5.2 Create a constructor
 *   5.3 Create object of IView interface
 *   5.4 Create object of Interactor
 * 6 In the MainActivity or Fragment
 *   6.1 Initialize the Presenter in onCreate
 *      6.1.2 Initialize the Interacter ( interface object = new Implementing class)
 *   6.2 Bind the presenter
 *   6.3 UnBind the presenter in OnDestroy()
 */
public interface Interactor {

    Observable<List<CakeListModel>> getCakes();

}
