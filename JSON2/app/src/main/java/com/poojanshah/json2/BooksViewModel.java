package com.poojanshah.json2;

import com.poojanshah.json2.model.Flower;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by shahp on 17/07/2017.
 */

public class BooksViewModel {

    private RequestInterface interactor;
    private Scheduler scheduler;

    public BooksViewModel(RequestInterface interactor, Scheduler scheduler) {
        this.interactor = interactor;
        this.scheduler = scheduler;
    }

    public Observable<List<Flower>> search() {
        return interactor.getFlowerList().observeOn(scheduler);
    }
}
