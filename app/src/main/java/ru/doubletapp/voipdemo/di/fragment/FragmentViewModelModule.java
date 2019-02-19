package ru.doubletapp.voipdemo.di.fragment;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.voipdemo.di.application.ViewModelFactory;
import ru.doubletapp.voipdemo.user_list.presentation.UserListFragment;
import ru.doubletapp.voipdemo.user_list.presentation.UserListViewModel;

@Module
public class FragmentViewModelModule {

    @Provides
    UserListViewModel provideUserListViewModel(UserListFragment fragment, ViewModelFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(UserListViewModel.class);
    }
}
