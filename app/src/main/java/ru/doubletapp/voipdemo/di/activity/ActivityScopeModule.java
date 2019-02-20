package ru.doubletapp.voipdemo.di.activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.doubletapp.voipdemo.MainActivity;
import ru.doubletapp.voipdemo.di.fragment.VoipScopeModule;

@Module
public abstract class ActivityScopeModule {

    @ContributesAndroidInjector(modules = {VoipScopeModule.class})
    abstract MainActivity scopeMainActivity();

}
