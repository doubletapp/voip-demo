package ru.doubletapp.voipdemo.di.fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.doubletapp.voipdemo.user_list.presentation.UserListFragment;

@Module
public abstract class VoipScopeModule {

    @ContributesAndroidInjector(modules = {FragmentViewModelModule.class})
    abstract UserListFragment userListFragment();
}
