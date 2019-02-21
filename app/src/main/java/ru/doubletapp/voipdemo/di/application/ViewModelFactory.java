package ru.doubletapp.voipdemo.di.application;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels) {
        mViewModels = viewModels;
    }

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>>  mViewModels;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> viewModelProvider = mViewModels.get(modelClass);
        if (viewModelProvider == null) {
            throw new IllegalArgumentException(String.format("View model class %s not found", modelClass));
        }

        //noinspection unchecked
        return (T) viewModelProvider.get();
    }
}
