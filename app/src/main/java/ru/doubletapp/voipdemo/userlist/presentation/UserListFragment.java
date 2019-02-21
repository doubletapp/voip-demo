package ru.doubletapp.voipdemo.userlist.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.doubletapp.voipdemo.R;
import ru.doubletapp.voipdemo.base.BaseFragment;
import ru.doubletapp.voipdemo.di.Injectable;

public class UserListFragment extends BaseFragment implements Injectable {

    public static final String TAG = "UserListFragment";

    @BindView(R.id.user_list_recycler)
    RecyclerView mRecyclerView;

    @Inject
    UserListViewModel viewModel;

    @NonNull
    private final UserListAdapter mAdapter = new UserListAdapter();

    @NonNull
    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,
                        false));

        mRecyclerView.setAdapter(mAdapter);

        viewModel.users().observe(this, userModels -> mAdapter.setUsers(userModels == null ? new ArrayList<>() : userModels));

        viewModel.errors().observe(this, s -> {
            if (s != null) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.user_list_fab)
    void onClick() {
        FragmentManager manager = getFragmentManager();
        if (manager != null) {
            viewModel.callSomeone(manager);
        }
    }
}
