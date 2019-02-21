package ru.doubletapp.voipdemo.call.domain;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class CallInteractor {

    @NonNull
    private final VoipGateway mVoipGateway;

    @NonNull
    private final MicrophoneGateway mMicrophoneGateway;

    public CallInteractor(@NonNull VoipGateway voipGateway,
                          @NonNull MicrophoneGateway microphoneGateway) {
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
