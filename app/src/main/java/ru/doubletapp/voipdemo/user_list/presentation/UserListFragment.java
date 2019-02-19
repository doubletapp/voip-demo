package ru.doubletapp.voipdemo.user_list.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.doubletapp.voipdemo.R;
import ru.doubletapp.voipdemo.base.BaseFragment;
import ru.doubletapp.voipdemo.di.Injectable;

public class UserListFragment extends BaseFragment implements Injectable {

    public static UserListFragment newInstance() {

        Bundle args = new Bundle();

        UserListFragment fragment = new UserListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.user_list_button)
    public void onTestClick() {
        Toast.makeText(getContext(), viewModel.test(), Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;

    }

    @Inject
    UserListViewModel viewModel;
}
