package ru.doubletapp.voipdemo.user_list.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.collect.Collections2;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.user_list.data.model.UserModel;
import ru.doubletapp.voipdemo.user_list.data.repository.local.UsersLocalRepository;

public class UserListInteractor {

    @Nullable
    private List<UserModel> mUsers;

    @NonNull
    private UsersLocalRepository mLocalRepository;

    @Inject
    public UserListInteractor(@NonNull UsersLocalRepository localRepository) {
        mLocalRepository = localRepository;
    }

    @NonNull
    public List<UserModel> getUsers() {
        if (mUsers == null) {
            mUsers = mLocalRepository.getUsers();
        }
        List<UserModel> users = mUsers;
        Collections.sort(users, (o1, o2) -> {
            if (o1.isOnline() == o2.isOnline()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return o1.isOnline() ? -1 : 1;
            }
        });
        return users;
    }

    @NonNull
    public List<UserModel> getOnlineUsers() {
        List<UserModel> allUsers = getUsers();
        Collection<UserModel> filtered = Collections2.filter(allUsers, input -> input != null && input.isOnline());
        return new ArrayList<>(filtered);
    }

}
