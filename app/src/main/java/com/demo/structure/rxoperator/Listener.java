package com.demo.structure.rxoperator;

import android.view.View;

import io.reactivex.rxjava3.android.MainThreadDisposable;
import io.reactivex.rxjava3.core.Observer;
import kotlin.Unit;

public class Listener extends MainThreadDisposable implements View.OnClickListener {
    Observer<? super Unit> observer;
    View view;

    public Listener(View view, Observer<? super Unit> observer) {
        this.view = view;
        this.observer = observer;
    }

    @Override
    protected void onDispose() {
        view.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        if (!isDisposed()) {
            observer.onNext(Unit);
        }
    }


}
