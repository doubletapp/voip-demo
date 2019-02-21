package ru.doubletapp.voipdemo.userlist.data.repository.local;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class UsersDao {

    @NonNull
    private final Set<Integer> mGenerated = new HashSet<>();

    @Inject
    UsersDao() {}

    /**
     * Get list of users
     * @return A list of random generated users
     */
    @NonNull
    List<UserModel> getUsers() {
        ArrayList<UserModel> users = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            users.add(generator());
        }
        return users;
    }

    /**
     * UserModel generator
     * @return new random UserModel
     */
    @NonNull
    private UserModel generator() {
        UserModel model = new UserModel();
        if (mGenerated.size() == names.length) {
            mGenerated.clear();
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, names.length);
        while (mGenerated.contains(randomNum)) {
            randomNum = ThreadLocalRandom.current().nextInt(0, names.length);
        }
        mGenerated.add(randomNum);
        model.setOnline(randomNum % 2 == 0);
        model.setName(names[randomNum]);
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
