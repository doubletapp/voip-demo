package ru.doubletapp.voipdemo.call.domain;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class CallInteractor {

    public CallInteractor() {}

    public Observable<String> makeCall(@NonNull String contact) {
        return Observable
                .interval(3, TimeUnit.SECONDS)
                .map(value -> value % 2 == 0 ? contact : "You")
                .take(6);
    }
}
