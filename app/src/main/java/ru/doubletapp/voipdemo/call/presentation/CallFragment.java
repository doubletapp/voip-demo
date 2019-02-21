package ru.doubletapp.voipdemo.call.presentation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.voipdemo.R;
import ru.doubletapp.voipdemo.base.BaseFragment;
import ru.doubletapp.voipdemo.di.Injectable;
import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class CallFragment extends BaseFragment implements Injectable {

    public static final String TAG = "CallFragment";

    private static final int REQUEST_CODE_MICROPHONE = 0x111C;
    private static final String ARGS_USER = "ARGS_USER";

    @BindView(R.id.call_status)
    TextView mCallStatus;

    @Inject
    CallViewModel viewModel;

    public static CallFragment newInstance(UserModel model) {

        Bundle args = new Bundle();
        args.putParcelable(ARGS_USER, model);

        CallFragment fragment = new CallFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.speaker().observe(this, s -> {
            if (s != null) {
                mCallStatus.setText(s);
            } else {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        viewModel.error().observe(this, s -> {
            if (s != null) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        if (isMicrophoneAccessGranted()) {
            makeCall();
        } else {
            requestMicrophonePermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_MICROPHONE) {
            if (isMicrophoneAccessGranted()) {
                makeCall();
            } else {
                Toast.makeText(requireContext(),
                        getString(R.string.ask_microphone),
                        Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean isMicrophoneAccessGranted() {
        return ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestMicrophonePermission() {
        requestPermissions(new String[] {Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_MICROPHONE);
    }

    private void makeCall() {
        if (getArguments() != null) {
            UserModel user = getArguments().getParcelable(ARGS_USER);
            if (user != null) {
                mCallStatus.setText(String.format(getString(R.string.callingto), user.getName()));
                viewModel.call(user);
            }
        }
    }
}
