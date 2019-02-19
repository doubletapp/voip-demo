package ru.doubletapp.voipdemo.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposables;

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}