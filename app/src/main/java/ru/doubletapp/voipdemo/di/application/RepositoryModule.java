package ru.doubletapp.voipdemo.di.application;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.voipdemo.user_list.data.repository.local.UsersDao;
import ru.doubletapp.voipdemo.user_list.data.repository.local.UsersLocalRepository;

@Module
class RepositoryModule {

    @Provides
    @Singleton
    UsersLocalRepository provideUsersLocalRepository(@NonNull UsersDao dao) {
        return new UsersLocalRepository(dao);
    }
}
