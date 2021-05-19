package com.dogfoot.insurancesystemapp.isApp.jungwoo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentPlanInsuranceSecondBinding;

public class PlanInsuranceSecondFragment extends Fragment {

    private FragmentPlanInsuranceSecondBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    private String strName, strPayment, strType;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentPlanInsuranceSecondBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        //initSpinner();
        init();

        return view;
    }

    private void init() {
        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // db에 전달
                strName =  mBinding.tvInsuranceNameCar.getText().toString();
                strPayment = mBinding.textView10.getText().toString();

                // 이후 strName strPayment strType 전달한다.
            }
        });
    }


//    private void initSpinner() {
//        Spinner insuranceSelectSpinner = mBinding.spinnerInsuranceSelect;
//        ArrayAdapter insuranceAdapter = ArrayAdapter.createFromResource(context.getApplicationContext(),
//                R.array.insurance_select, android.R.layout.simple_spinner_item);
//        insuranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        insuranceSelectSpinner.setAdapter(insuranceAdapter);
//
//        insuranceSelectSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    strType = adapterView.getItemAtPosition(i).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//
//        });
//        });
//
//    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbPlanInsuranceToolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                replaceFragment(HomeFragment.newInstance());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static PlanInsuranceSecondFragment newInstance() {
        return new PlanInsuranceSecondFragment();
    }
}