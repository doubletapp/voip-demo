package ru.doubletapp.voipdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.doubletapp.voipdemo.base.BaseActivity;
import ru.doubletapp.voipdemo.user_list.presentation.UserListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, UserListFragment.newInstance())
                    .commit();
        }
    }
}
