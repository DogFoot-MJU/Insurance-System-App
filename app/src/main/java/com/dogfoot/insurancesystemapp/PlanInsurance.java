package com.dogfoot.insurancesystemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.dogfoot.insurancesystemapp.databinding.ActivityMainBinding;
import com.dogfoot.insurancesystemapp.databinding.ActivityPlanInsuranceBinding;

import java.util.ArrayList;

public class PlanInsurance extends AppCompatActivity {

    private ActivityPlanInsuranceBinding mBinding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<InsurancePlanning> insuarancePlanningList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_insurance);

        // Set Binding
        mBinding = ActivityPlanInsuranceBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        insuarancePlanningList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvInsurancePlanning.setLayoutManager(linearLayoutManager);

        setContentInsurance();
    }

    private void setContentInsurance() {

    }
}