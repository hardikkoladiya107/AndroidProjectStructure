package com.demo.structure.rxoperator;

import android.view.View;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import kotlin.Unit;

public class ObservableClick extends Observable<Unit> {

    View nView;

    public ObservableClick(View view) {
        nView = view;
    }

    @Override
    protected void subscribeActual(@NonNull Observer<? super Unit> observer) {
        Listener listener = new Listener(nView, observer);
        observer.onSubscribe(listener);
        nView.setOnClickListener(listener);
    }



}
