package ru.doubletapp.voipdemo.di.application;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ru.doubletapp.voipdemo.call.presentation.CallViewModel;
import ru.doubletapp.voipdemo.userlist.presentation.UserListViewModel;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel.class)
    abstract ViewModel provideUserListViewModel(UserListViewModel userListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CallViewModel.class)
    abstract ViewModel provideCallViewModel(CallViewModel callViewModel);
}
