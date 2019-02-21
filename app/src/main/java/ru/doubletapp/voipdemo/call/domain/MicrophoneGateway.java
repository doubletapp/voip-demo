package ru.doubletapp.voipdemo.call.domain;

import android.content.Context;
import android.media.AudioManager;
import android.support.annotation.NonNull;

public class MicrophoneGateway {

    @NonNull
    private final Context mContext;

    public MicrophoneGateway(@NonNull Context context) {
        mContext = context;
    }

    /**
     * Mute microphone
     */
    void mute() {
        AudioManager audioManager =
                (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        int mode = audioManager.getMode();
        if (mode == AudioManager.MODE_IN_CALL || mode == AudioManager.MODE_IN_COMMUNICATION) {
            audioManager.setMicrophoneMute(true);
        }
    }

    /**
     * Cancel microphone mute
     */
    void unmute() {
        AudioManager audioManager =
                (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        int mode = audioManager.getMode();
        if (mode == AudioManager.MODE_IN_CALL || mode == AudioManager.MODE_IN_COMMUNICATION) {
            audioManager.setMicrophoneMute(false);
        }
    }
}
