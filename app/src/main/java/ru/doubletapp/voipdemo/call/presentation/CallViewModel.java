package ru.doubletapp.voipdemo.call.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.base.BaseViewModel;
import ru.doubletapp.voipdemo.call.domain.CallInteractor;
import ru.doubletapp.voipdemo.user_list.data.model.UserModel;

public class CallViewModel extends BaseViewModel {

    @NonNull
    private CallInteractor mCallInteractor;

    private MutableLiveData<String> mMutableSpeaker = new MutableLiveData<>();
    private MutableLiveData<String> mMutableError = new MutableLiveData<>();

    LiveData<String> speaker() { return mMutableSpeaker; }
    LiveData<String> error() { return  mMutableError; }

    @Inject
    CallViewModel(@NonNull CallInteractor callInteractor) {
        mCallInteractor = callInteractor;
    }

    public void call(@NonNull UserModel userModel) {
        disposables.add(mCallInteractor.makeCall(userModel.getName()).subscribe(
                s -> mMutableSpeaker.postValue(s + " speak"),
                e -> mMutableError.postValue(e.getLocalizedMessage()),
                () -> mMutableSpeaker.postValue(null)));
    }

}
