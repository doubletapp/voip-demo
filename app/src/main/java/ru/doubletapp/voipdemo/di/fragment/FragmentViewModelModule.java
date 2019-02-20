package ru.doubletapp.voipdemo.di.fragment;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.voipdemo.call.presentation.CallFragment;
import ru.doubletapp.voipdemo.call.presentation.CallViewModel;
import ru.doubletapp.voipdemo.di.application.ViewModelFactory;
import ru.doubletapp.voipdemo.userlist.presentation.UserListFragment;
import ru.doubletapp.voipdemo.userlist.presentation.UserListViewModel;

@Module
public class FragmentViewModelModule {

    @Provides
    UserListViewModel provideUserListViewModel(UserListFragment fragment, ViewModelFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(UserListViewModel.class);
    }

    @Provides
    CallViewModel provideCallViewModel(CallFragment fragment, ViewModelFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(CallViewModel.class);
    }
}
