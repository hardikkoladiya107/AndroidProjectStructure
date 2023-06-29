package com.demo.structure.example_rxjava;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityRxJavaSampleBinding;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.SerialDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RxJavaSampleActivity extends AppCompatActivity {


    ActivityRxJavaSampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_sample);

        binding.rxbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //performRxJavaOperations();
                complexObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(complexObserver());

                complexFlowable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(flowableSubscriberObserver());

            }
        });
    }

    private void performRxJavaOperations() {
        getlist().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<String> strings) {
                Log.e("TAG", "onNext: " + strings);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    Observable<ArrayList<String>> getlist() {
        ArrayList<String> animals = new ArrayList();
        animals.add("Tiger");
        animals.add("Lion");
        animals.add("Elephant");
        return Observable.just(animals);
    }

    Observable<ArrayList<String>> complexObservable() {
        ArrayList<String> animals = new ArrayList();
        animals.add("Tiger");
        animals.add("Lion");
        animals.add("Elephant");
        return Observable.create(new ObservableOnSubscribe<ArrayList<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<String>> emitter) throws Throwable {
                emitter.onNext(animals);
                emitter.onNext(animals);
                if (!emitter.isDisposed()) {
                    emitter.onNext(animals);
                }
                emitter.onComplete();

            }
        });
    }

    Observer<ArrayList<String>> complexObserver() {
        return new Observer<ArrayList<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<String> strings) {
                Log.e("TAG", "onNext: " + strings);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onNext: " + "Complete");
            }
        };
    }

    Flowable<ArrayList<String>> complexFlowable() {
        ArrayList<String> animals = new ArrayList();
        animals.add("Tiger");
        animals.add("Lion");
        animals.add("Elephant");
        return Flowable.create(new FlowableOnSubscribe<ArrayList<String>>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<ArrayList<String>> emitter) throws Throwable {
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onNext(animals);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }

    FlowableSubscriber<ArrayList<String>> flowableSubscriberObserver() {
        return new FlowableSubscriber<ArrayList<String>>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {

            }

            @Override
            public void onNext(ArrayList<String> strings) {
                Log.e("TAG", "onNext: " + strings);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete: ");
            }
        };
    }

    Single<String> singleExample() {
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> emitter) throws Throwable {
                emitter.onSuccess("Single Example");
            }
        });
    }

    SingleObserver<String> singleObserverExample() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
    }

    Maybe<String> maybeObservaleExample() {
        return Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Throwable {
                emitter.onSuccess("Maybe Example");
            }
        });
    }

    MaybeObserver<String> maybeObserverExample() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    Completable completableObservableExample() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    CompletableObserver completableObserverExample() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
    }

    void coldObservableExample() throws InterruptedException {
        Observable<Long> myObservable = Observable.interval(1, TimeUnit.SECONDS);
        myObservable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {

            }
        });
        Thread.sleep(3000);
    }

    Disposable disposable;
    Disposable disposable2;
    CompositeDisposable compositeDisposable;

    void disposableExample() {
        compositeDisposable = new CompositeDisposable();

        disposable = complexObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ArrayList<String>>() {
            @Override
            public void onNext(@NonNull ArrayList<String> strings) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        /*disposable2 = complexObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<ArrayList<String>>(){
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<String> strings) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        compositeDisposable.add(disposable);
    }

    SerialDisposable serialDisposable = new SerialDisposable();

    void serialDisposible() {
        serialDisposable.set(complexObservable().observeOn(AndroidSchedulers.mainThread()).subscribe());
    }


    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            compositeDisposable.clear();
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }


}