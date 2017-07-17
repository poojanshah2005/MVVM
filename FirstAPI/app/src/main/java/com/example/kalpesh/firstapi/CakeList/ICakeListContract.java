package com.example.kalpesh.firstapi.CakeList;

import com.example.kalpesh.firstapi.model.CakeListModel;

import java.util.List;

/**
 * Created by kalpesh on 16/04/2017.
 */

public interface ICakeListContract {

     interface IView
     {
         void shoProgressDisalog();
         void showCakesList(List<CakeListModel> cakeListModels);
         void dismissProgressDialog();

     }


    interface IPresenter
    {
        void bind(IView view);
        void unBind();
        void getCakeList();
    }
}
