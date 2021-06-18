package com.dogfoot.insurancesystemapp.isApp.dongwook;

import android.os.Bundle;


import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view.CustomerCompensationMainActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingMainActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.view.LoginActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.logout.view.LogOutActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view.SalesCompensationMainActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view.SalesConsultingMainActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view.SignUpActivity;


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
        this.findViewById(R.id.dongWookActivity_signUpButton).setOnClickListener(v -> this.startActivity(SignUpActivity.class));
        this.findViewById(R.id.dongWookActivity_logoutButton).setOnClickListener(v -> this.startActivity(LogOutActivity.class));
        this.findViewById(R.id.dongWookActivity_customerConsultingButton).setOnClickListener(v -> this.startActivity(CustomerConsultingMainActivity.class));
        this.findViewById(R.id.dongWookActivity_adminConsultingButton).setOnClickListener(v -> this.startActivity(SalesConsultingMainActivity.class));
        this.findViewById(R.id.dongWookActivity_customerCompensation_Button).setOnClickListener(v -> this.startActivity(CustomerCompensationMainActivity.class));
        this.findViewById(R.id.dongWookActivity_salesCompensation_Button).setOnClickListener(v -> this.startActivity(SalesCompensationMainActivity.class));


    }

    @Override
    public void dogFootEntityUpdated() {

    }

}