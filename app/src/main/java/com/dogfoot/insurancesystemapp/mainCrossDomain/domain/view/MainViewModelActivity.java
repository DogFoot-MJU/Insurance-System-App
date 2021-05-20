package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.viewModel.MainViewModelTool;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.viewModel.ViewModelToolCallback;

public abstract class MainViewModelActivity extends AppCompatActivity implements ViewModelToolCallback {
    // Component
    protected MainViewModelTool viewModelTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create Component
        this.viewModelTool = new MainViewModelTool(this,this, this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        this.viewModelTool.startObserve();
    }
    @Override
    protected void onPause() {
        super.onPause();
        this.viewModelTool.stopObserve();
    }
}
