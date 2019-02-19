package ru.doubletapp.voipdemo.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseFragment extends Fragment implements HasSupportFragmentInjector {

    @Nullable
    protected Unbinder mUnbinder;

    @Inject
    protected DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mDispatchingAndroidInjector;
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }
}
