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

    @NonNull
    LiveData<String> speaker() { return mMutableSpeaker; }

    @NonNull
    LiveData<String> error() { return  mMutableError; }

    @Inject
    CallViewModel(@NonNull CallInteractor callInteractor) {
        mCallInteractor = callInteractor;
    }

    public void call(@NonNull UserModel userModel) {
        mDisposables.add(mCallInteractor.makeCall(userModel.getName()).subscribe(
                mMutableSpeaker::postValue,
                e -> mMutableError.postValue(e.getLocalizedMessage()),
                () -> mMutableSpeaker.postValue(null)));
    }

}
