package ru.doubletapp.voipdemo.call.domain;

import android.support.annotation.NonNull;
import javax.annotation.Nonnull;
import io.reactivex.Observable;

public class CallInteractor {

    @Nonnull
    VoipGateway mVoipGateway;

    @Nonnull
    MicrophoneGateway mMicrophoneGateway;

    public CallInteractor(@Nonnull VoipGateway voipGateway,
                          @Nonnull MicrophoneGateway microphoneGateway) {
        mVoipGateway = voipGateway;
        mMicrophoneGateway = microphoneGateway;
    }

    public Observable<String> makeCall(@NonNull String contact) {

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
                  return contact + " speaks";
                  default:
                      return "Disconnected";
          }
        });
    }
}
