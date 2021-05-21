package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentPlanInsuranceSecondBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanInsuranceSecondFragment extends DogFootViewModelFragment {

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
        initSpinner();
        init();

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

    private void init() {
        mBinding.btnPlanInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // db에 전달
                checkInsuranceType();
            }

        });
    }

    private void checkInsuranceType() {
        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        strName =  mBinding.etPlanInsuranceNameWrite.getText().toString();
        strPayment = mBinding.etPlanInsurancePaymentWrite.getText().toString();

        if(strType.equals("자동차 보험")) {
            RetrofitTool.getAPIWithAuthorizationToken(token).planCarInsurance(new CarPlanInsuranceRequest(strName, Long.valueOf(strPayment)))
                    .enqueue(new Callback<CarPlanInsuranceResponse>() {
                @Override
                public void onResponse(Call<CarPlanInsuranceResponse> call, Response<CarPlanInsuranceResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(fragmentContext, "보험 기획을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    } else{
                    }
                }
                @Override
                public void onFailure(Call<CarPlanInsuranceResponse> call, Throwable t) { }
            });
        } else if(strType.equals("운전자 보험")) {
            RetrofitTool.getAPIWithAuthorizationToken(token).planDriverInsurance(new DriverPlanInsuranceRequest(strName, Long.valueOf(strPayment)))
                    .enqueue(new Callback<DriverPlanInsuranceResponse>() {
                @Override
                public void onResponse(Call<DriverPlanInsuranceResponse> call, Response<DriverPlanInsuranceResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(fragmentContext, "보험 기획을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    } else{
                    }
                }
                @Override
                public void onFailure(Call<DriverPlanInsuranceResponse> call, Throwable t) { }
            });
        }else if(strType.equals("화재 보험")) {
            RetrofitTool.getAPIWithAuthorizationToken(token).planFireInsurance(new FirePlanInsuranceRequest(strName, Long.valueOf(strPayment)))
                    .enqueue(new Callback<FirePlanInsuranceResponse>() {
                @Override
                public void onResponse(Call<FirePlanInsuranceResponse> call, Response<FirePlanInsuranceResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(fragmentContext, "보험 기획을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    } else{
                    }
                }
                @Override
                public void onFailure(Call<FirePlanInsuranceResponse> call, Throwable t) { }
            });
        }
        else if(strType.equals("여행 보험")) {
            RetrofitTool.getAPIWithAuthorizationToken(token).planTravelInsurance(new TravelPlanInsuranceRequest(strName, Long.valueOf(strPayment)))
                    .enqueue(new Callback<TravelPlanInsuranceResponse>() {
                @Override
                public void onResponse(Call<TravelPlanInsuranceResponse> call, Response<TravelPlanInsuranceResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(fragmentContext, "보험 기획을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    } else{
                    }
                }
                @Override
                public void onFailure(Call<TravelPlanInsuranceResponse> call, Throwable t) { }
            });
        }
        replaceFragment(HomeFragment.newInstance());
    }


    private void initSpinner() {
        Spinner insuranceSelectSpinner = mBinding.spinnerInsuranceSelect;

        insuranceSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("자동차 보험")){
                    strType = "자동차 보험";
                } else if(adapterView.getItemAtPosition(i).equals("운전자 보험")){
                    strType = "운전자 보험";
                } else if(adapterView.getItemAtPosition(i).equals("화재 보험")){
                    strType = "화재 보험";
                } else if(adapterView.getItemAtPosition(i).equals("여행 보험")){
                    strType = "여행 보험";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


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