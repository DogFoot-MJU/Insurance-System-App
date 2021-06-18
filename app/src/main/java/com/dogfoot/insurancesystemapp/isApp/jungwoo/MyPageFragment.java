package com.dogfoot.insurancesystemapp.isApp.jungwoo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dogfoot.insurancesystemapp.databinding.ActivityMyPageFragmentBinding;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;

public class MyPageFragment extends DogFootViewModelFragment {

    private ActivityMyPageFragmentBinding mBinding;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ActivityMyPageFragmentBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        return view;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void associateView(View view) {

    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void dogFootEntityUpdated() {

    }

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }
}