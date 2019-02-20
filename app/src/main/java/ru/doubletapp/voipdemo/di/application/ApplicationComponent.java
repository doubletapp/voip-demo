package ru.doubletapp.voipdemo.di.application;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.doubletapp.voipdemo.VoipDemoApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ViewModelModule.class,
        InteractorModule.class,
        RepositoryModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder with(Application application);

        ApplicationComponent build();
    }

    void inject(VoipDemoApplication application);
}
