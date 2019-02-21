package ru.doubletapp.voipdemo.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected final CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    protected void onCleared() {
        mDisposables.clear();
        super.onCleared();
    }
}
