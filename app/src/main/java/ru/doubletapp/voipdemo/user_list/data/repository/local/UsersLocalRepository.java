package ru.doubletapp.voipdemo.user_list.data.repository.local;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.user_list.data.model.UserModel;

public class UsersLocalRepository {

    @NonNull
    private UsersDao mUsersDao;

    @Inject
    public UsersLocalRepository(@NonNull UsersDao dao) {
        mUsersDao = dao;
    }

    @NonNull
    public List<UserModel> getUsers() {
        return mUsersDao.getUsers();
    }


}
