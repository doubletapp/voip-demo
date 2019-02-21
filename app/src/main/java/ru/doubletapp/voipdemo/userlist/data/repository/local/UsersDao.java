package ru.doubletapp.voipdemo.userlist.data.repository.local;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class UsersDao {

    @NonNull
    private final Random mRandom = new Random();

    @Inject
    UsersDao() {
    }

    /**
     * Get list of users
     *
     * @return A list of random generated users
     */
    @NonNull
    List<UserModel> getUsers() {
        ArrayList<UserModel> users = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            users.add(generator(i));
        }
        return users;
    }

    /**
     * UserModel generator
     *
     * @return new UserModel
     */
    @NonNull
    private UserModel generator(int index) {
        UserModel model = new UserModel();
        model.setOnline(mRandom.nextDouble() > .5);
        model.setName(names[index]);
        return model;
    }

    private static final String[] names = {
            "Lavinia Heffron", "Richelle Parmelee",
            "Elma Plemons", "Broderick Kosakowski",
            "Barrie Huitt", "Eliseo Scogin",
            "Lucas Hermann", "Ellsworth Dusseault",
            "Ron Erben", "Linda Gendron",
            "Dannie Sugarman", "Corinne Muldrow",
            "Cyndi Kopp", "Aleshia Cratty",
            "Carrie Stoudt", "Alex Melbourne"
    };
}
