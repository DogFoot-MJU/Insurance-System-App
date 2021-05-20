package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.view;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.viewModel.MainViewModelTool;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.viewModel.ViewModelToolCallback;

public abstract class MainViewModelFragment extends Fragment implements ViewModelToolCallback {
    // Component
    protected MainViewModelTool viewModelTool;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create Component
        this.viewModelTool = new MainViewModelTool(this.getActivity(), this,this);
    }
    @Override
    public void onStart() {
        super.onStart();
        this.viewModelTool.startObserve();
    }

    @Override
    public void onResume() {
        super.onResume();
//        this.viewModelTool.startObserve();
    }
    @Override
    public void onPause() {
        super.onPause();
//        this.viewModelTool.stopObserve();
    }

    @Override
    public void onStop() {
        super.onStop();
        this.viewModelTool.stopObserve();
    }
}
