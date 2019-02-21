package ru.doubletapp.voipdemo.call.domain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class VoipGateway implements Session.SessionListener, PublisherKit.PublisherListener {

    enum VoipStatus {
        Connecting,
        Speaking,
        Listening
    }

    private static final String LOG_TAG = "VOIP GATEWAY";

    private static final int DURATION_LIMIT = 6;
    private static final int SPEAK_INTERVAL_MS = 3000;

    private static final String API_KEY = "46272912";
    private static final String SESSION_ID = "2_MX40NjI3MjkxMn5-MTU1MDczMjM2NzA2NH45bGtqRVZ5NHhUcnJLZmVBTjkyUmtKa05-UH4";
    private static final String TOKEN =
            "T1==cGFydG5lcl9pZD00NjI3MjkxMiZzaWc9OGY4NTZhODBjMzZlMjQ2YzFhZ" +
            "mU1M2ZiMGJlYjgyNDczYjBjM2Q4NjpzZXNzaW9uX2lkPTJfTVg0ME5qSTNNamt4TW41LU1UVTFNRGN6TW" +
            "pNMk56QTJOSDQ1Ykd0cVJWWjVOSGhVY25KTFptVkJUamt5VW10S2EwNS1VSDQmY3JlYXRlX3RpbWU9MT"  +
            "U1MDczMjQxNiZub25jZT0wLjkwNDIwMzIzNTY0NzY3MTcmcm9sZT1wdWJsaXNoZXImZXhwaXJlX3RpbWU9" +
                    "MTU1MzMyMDgxNSZpbml0aWFsX2xheW91dF9jbGFzc19saXN0PQ==";

    @Nullable
    private Session mSession;

    @Nullable
    private Subscriber mSubscriber;

    @NonNull
    private WeakReference<Context> mWeakContext;

    @Nullable
    private BehaviorSubject<VoipStatus> mStatusObservable;

    @Nullable
    private Timer mMicrophoneTimer;

    private boolean mMicrophoneMuted = false;
    private int mDurationCounter = 0;

    public VoipGateway(@NonNull Context context) {
        mWeakContext = new WeakReference<>(context);
    }

    Observable<VoipStatus> startSession() {
        mStatusObservable = BehaviorSubject.create();
        if (mWeakContext.get() != null) {
            mSession = new Session.Builder(mWeakContext.get(), API_KEY, SESSION_ID).build();
            mSession.setSessionListener(this);
            mSession.connect(TOKEN);
            mStatusObservable.onNext(VoipStatus.Connecting);
        } else {
            mStatusObservable.onError(new IllegalStateException("No context"));
        }
        return mStatusObservable;
    }

    // region SessionListener

    @Override
    public void onConnected(Session session) {

        Publisher publisher = new Publisher.Builder(mWeakContext.get()).build();
        publisher.setPublisherListener(this);
        if (mSession != null) {
            mSession.publish(publisher);
        }
        if (mStatusObservable != null) {
            mStatusObservable.onNext(VoipStatus.Speaking);
        }

        mDurationCounter = 0;
        mMicrophoneTimer = new Timer();
        mMicrophoneTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mMicrophoneMuted = !mMicrophoneMuted;
                mDurationCounter++;
                if (mDurationCounter == DURATION_LIMIT) {
                    mSession.disconnect();
                    mMicrophoneTimer.cancel();
                }
                if (mStatusObservable != null) {
                    mStatusObservable.onNext(
                            mMicrophoneMuted ? VoipStatus.Listening : VoipStatus.Speaking);
                }
            }
        }, SPEAK_INTERVAL_MS, SPEAK_INTERVAL_MS);
    }

    @Override
    public void onDisconnected(Session session) {
        if (mStatusObservable != null) {
            mStatusObservable.onComplete();
        }
    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {
        Log.i(LOG_TAG, "Stream Received");

        if (mSubscriber == null) {
            mSubscriber = new Subscriber.Builder(mWeakContext.get(), stream).build();
            if (mSession != null) {
                mSession.subscribe(mSubscriber);
            }
        }
    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {
        Log.i(LOG_TAG, "Stream Dropped");

        if (mSubscriber != null) {
            mSubscriber = null;
        }
    }

    @Override
    public void onError(Session session, OpentokError opentokError) {
        if (mStatusObservable != null) {
            mStatusObservable.onError(new Exception(opentokError.getMessage()));
        }
    }

    // endregion SessionListener

    // region PublisherListener

    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {
        Log.i(LOG_TAG, "Publisher onStreamCreated");
    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {
        Log.i(LOG_TAG, "Publisher onStreamDestroyed");
    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {
        Log.e(LOG_TAG, "Publisher error: " + opentokError.getMessage());
    }

    // endregion PublisherListener


}
