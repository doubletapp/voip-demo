package ru.doubletapp.voipdemo.userlist.data.repository.local;

import android.support.annotation.NonNull;

import java.util.List;

import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class UsersLocalRepository {

    @NonNull
    private UsersDao mUsersDao;

    public UsersLocalRepository(@NonNull UsersDao dao) {
        mUsersDao = dao;
    }

    @NonNull
    public List<UserModel> getUsers() {
        return mUsersDao.getUsers();
    }
}
