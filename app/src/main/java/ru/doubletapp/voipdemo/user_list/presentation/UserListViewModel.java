package ru.doubletapp.voipdemo.user_list.presentation;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.base.BaseViewModel;

public class UserListViewModel extends BaseViewModel {

    @Inject
    UserListViewModel() {}

    public String test() {
        return "text";
    }
}
