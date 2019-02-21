package ru.doubletapp.voipdemo.call.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.base.BaseViewModel;
import ru.doubletapp.voipdemo.call.domain.CallInteractor;
import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class CallViewModel extends BaseViewModel {

    @NonNull
    private final CallInteractor mCallInteractor;

    @NonNull
    private final MutableLiveData<String> mMutableSpeaker = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> mMutableError = new MutableLiveData<>();

    /**
     * Get LiveData for determining current speaker
     */
    @NonNull
    LiveData<String> speaker() { return mMutableSpeaker; }

    /**
     * Get LiveData for error handling
     */
    @NonNull
    LiveData<String> error() { return  mMutableError; }

    @Inject
    CallViewModel(@NonNull CallInteractor callInteractor) {
        mCallInteractor = callInteractor;
    }

    /**
     * Call to user
     * @param userModel model for calling
     */
    public void call(@NonNull UserModel userModel) {
        mDisposables.add(mCallInteractor.makeCall(userModel).subscribe(
                mMutableSpeaker::postValue,
                e -> mMutableError.postValue(e.getLocalizedMessage()),
                () -> mMutableSpeaker.postValue(null)));
    }

}
