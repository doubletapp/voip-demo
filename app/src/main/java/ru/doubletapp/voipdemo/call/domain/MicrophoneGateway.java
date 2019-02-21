package ru.doubletapp.voipdemo.call.domain;

import android.content.Context;
import android.media.AudioManager;

import java.lang.ref.WeakReference;

import javax.annotation.Nonnull;

public class MicrophoneGateway {


    public MicrophoneGateway(@Nonnull Context context) {
        mContextReference = new WeakReference<>(context);
    }

    private WeakReference<Context> mContextReference;

    void mute() {
        AudioManager audioManager =
                (AudioManager) mContextReference.get().getSystemService(Context.AUDIO_SERVICE);
        int mode = audioManager.getMode();
        if (mode == AudioManager.MODE_IN_CALL || mode == AudioManager.MODE_IN_COMMUNICATION) {
            audioManager.setMicrophoneMute(true);
        }
    }

    void unmute() {
        AudioManager audioManager =
                (AudioManager) mContextReference.get().getSystemService(Context.AUDIO_SERVICE);
        int mode = audioManager.getMode();
        if (mode == AudioManager.MODE_IN_CALL || mode == AudioManager.MODE_IN_COMMUNICATION) {
            audioManager.setMicrophoneMute(true);
        }
    }
}
