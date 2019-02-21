package ru.doubletapp.voipdemo.di.application;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.voipdemo.call.domain.MicrophoneGateway;
import ru.doubletapp.voipdemo.call.domain.VoipGateway;
import ru.doubletapp.voipdemo.userlist.data.repository.local.UsersDao;
import ru.doubletapp.voipdemo.userlist.data.repository.local.UsersLocalRepository;

@Module
class RepositoryModule {

    @Provides
    @Singleton
    UsersLocalRepository provideUsersLocalRepository(@NonNull UsersDao dao) {
        return new UsersLocalRepository(dao);
    }

    @Provides
    @Singleton
    VoipGateway provideVoipGateway(@Nonnull Context context) {
        return new VoipGateway(context);
    }

    @Provides
    @Singleton
    MicrophoneGateway provideMicrophoneGateway(@Nonnull Context context) {
        return new MicrophoneGateway(context);
    }
}
