package ru.doubletapp.voipdemo.di.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.voipdemo.di.activity.ActivityScopeModule;

@Module(includes = {ActivityScopeModule.class})
class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}
