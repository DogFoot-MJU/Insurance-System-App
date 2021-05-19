package com.dogfoot.insurancesystemapp.isApp.jungwoo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dogfoot.insurancesystemapp.databinding.ActivityMyPageFragmentBinding;

public class MyPageFragment extends Fragment {

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

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }
}