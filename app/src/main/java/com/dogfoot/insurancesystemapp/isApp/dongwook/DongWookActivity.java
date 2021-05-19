package com.dogfoot.insurancesystemapp.isApp.dongwook;

import android.os.Bundle;


import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.view.LoginActivity;


public class DongWookActivity extends DogFootViewModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_wook);

    }
    @Override protected void associateView() { }
    @Override
    protected void initializeView() {
        this.findViewById(R.id.dongWookActivity_loginButton).setOnClickListener(v -> this.startActivity(LoginActivity.class));
        this.findViewById(R.id.dongWookActivity_signUpButton).setOnClickListener(v -> this.startActivity(LoginActivity.class));

    }

    @Override
    public void dogFootEntityUpdated() {

    }

}