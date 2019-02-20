package ru.doubletapp.voipdemo.di.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.voipdemo.call.domain.CallInteractor;
import ru.doubletapp.voipdemo.user_list.data.repository.local.UsersLocalRepository;
import ru.doubletapp.voipdemo.user_list.domain.UserListInteractor;

@Module
class InteractorModule {

    @Provides
    @Singleton
    UserListInteractor provideUserListInteractor(UsersLocalRepository repository) {
        return new UserListInteractor(repository);
    }

    @Provides
    @Singleton
    CallInteractor provideCallInteractor() {
        return new CallInteractor();
    }
}
