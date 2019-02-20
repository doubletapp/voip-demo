package ru.doubletapp.voipdemo.user_list.domain;

import android.support.annotation.NonNull;

import com.google.common.collect.Collections2;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.user_list.data.model.UserModel;
import ru.doubletapp.voipdemo.user_list.data.repository.local.UsersLocalRepository;

public class UserListInteractor {

    @NonNull
    private UsersLocalRepository mLocalRepository;

    @Inject
    public UserListInteractor(@NonNull UsersLocalRepository localRepository) {
        mLocalRepository = localRepository;
    }

    @NonNull
    public List<UserModel> getUsers() {
        List<UserModel> users = mLocalRepository.getUsers();
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
