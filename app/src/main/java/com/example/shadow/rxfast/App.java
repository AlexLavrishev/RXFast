package com.example.shadow.rxfast;

import java.util.Random;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import android.R;
/**
 * Created by shadow on 14/11/16.
 */

public class App {
    private static final String TAG = "";
    public static  void main(String[] args){
        Observable.range(1, 50)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return Observable.just(integer)
                                .map(new Func1<Integer, Integer>() {
                                    @Override
                                    public Integer call(Integer integer) {
                                        return dd(integer);
                                    }
                                })
                                .subscribeOn(Schedulers.computation());
                    }
                })
                .toBlocking()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer s) {
                        pr(s);
                    }
                });
    }

    private static int dd(int i){
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() );
        return i;
    }

    private static void pr(int i){
        System.out.println(Thread.currentThread().getName() + " " + i);
    }
}
