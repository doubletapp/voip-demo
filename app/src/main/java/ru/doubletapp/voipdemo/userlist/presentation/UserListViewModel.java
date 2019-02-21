package ru.doubletapp.voipdemo.userlist.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.R;
import ru.doubletapp.voipdemo.base.BaseViewModel;
import ru.doubletapp.voipdemo.call.presentation.CallFragment;
import ru.doubletapp.voipdemo.userlist.data.model.UserModel;
import ru.doubletapp.voipdemo.userlist.domain.UserListInteractor;

public class UserListViewModel extends BaseViewModel {

    @NonNull
    private UserListInteractor mUserListInteractor;

    private MutableLiveData<List<UserModel>> mMutableUsers = new MutableLiveData<>();
    private MutableLiveData<String> mMutableErrors = new MutableLiveData<>();

    /**
     * Get LiveData of users list
     */
    LiveData<List<UserModel>> users() { return mMutableUsers; }

    /**
     * Get LiveData for error handling
     */
    LiveData<String> errors() { return  mMutableErrors; }

    @Inject
    UserListViewModel(@NonNull UserListInteractor interactor) {
        mUserListInteractor = interactor;
        mMutableUsers.setValue(mUserListInteractor.getUsers());
    }

    /**
     * Call to random online user if any
     * @param manager Fragment manager for replacing fragment
     */
    void callSomeone(@NonNull FragmentManager manager) {
        List<UserModel> online = mUserListInteractor.getOnlineUsers();
        if (online.size() == 0) {
            mMutableErrors.setValue("No one is online");
            mMutableErrors.setValue(null);
        } else {
            int order = ThreadLocalRandom.current().nextInt(0, online.size());
            manager.beginTransaction()
                    .replace(R.id.fragment_container, CallFragment.newInstance(online.get(order)))
                    .addToBackStack(CallFragment.TAG)
                    .commit();
        }
    }
}
