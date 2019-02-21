package ru.doubletapp.voipdemo.call.domain;

import android.support.annotation.NonNull;
import javax.annotation.Nonnull;
import io.reactivex.Observable;
import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class CallInteractor {

    @Nonnull
    private VoipGateway mVoipGateway;

    @Nonnull
    private MicrophoneGateway mMicrophoneGateway;

    public CallInteractor(@Nonnull VoipGateway voipGateway,
                          @Nonnull MicrophoneGateway microphoneGateway) {
        mVoipGateway = voipGateway;
        mMicrophoneGateway = microphoneGateway;
    }

    /**
     * Initiates call with user
     * @param model user to call
     */
    public Observable<String> makeCall(@NonNull UserModel model) {
        return mVoipGateway.startSession().doOnEach(s -> {
            if (s.getValue() == VoipGateway.VoipStatus.Listening) {
                mMicrophoneGateway.mute();
            } else if (s.getValue() == VoipGateway.VoipStatus.Speaking) {
                mMicrophoneGateway.unmute();
            }
        }).map(voipStatus -> {
          switch (voipStatus) {
              case Connecting:
                  return "Connecting";
              case Speaking:
                  return "You speak";
              case Listening:
                  return model.getName() + " speaks";
                  default:
                      return "Disconnected";
          }
        });
    }
}
