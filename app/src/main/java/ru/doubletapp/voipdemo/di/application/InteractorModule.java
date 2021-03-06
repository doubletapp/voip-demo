package ru.doubletapp.voipdemo.di.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.voipdemo.call.domain.CallInteractor;
import ru.doubletapp.voipdemo.call.domain.MicrophoneGateway;
import ru.doubletapp.voipdemo.call.domain.VoipGateway;
import ru.doubletapp.voipdemo.userlist.data.repository.local.UsersLocalRepository;
import ru.doubletapp.voipdemo.userlist.domain.UserListInteractor;

@Module
class InteractorModule {

    @Provides
    @Singleton
    UserListInteractor provideUserListInteractor(UsersLocalRepository repository) {
        return new UserListInteractor(repository);
    }

    @Provides
    @Singleton
    CallInteractor provideCallInteractor(VoipGateway voipGateway, MicrophoneGateway microphoneGateway) {
        return new CallInteractor(voipGateway, microphoneGateway);
    }
}
