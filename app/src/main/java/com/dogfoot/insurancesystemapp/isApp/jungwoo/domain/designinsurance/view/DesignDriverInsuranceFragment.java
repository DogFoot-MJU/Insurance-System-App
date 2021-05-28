package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignDriverInsuranceBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignDriverInsuranceFragment extends DogFootViewModelFragment {

    private FragmentDesignDriverInsuranceBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    private String strLicense;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDesignDriverInsuranceBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        initData();
        init();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        mBinding.tvDesignId2.setText(bundle.getString("strId"));
        mBinding.tvDesignName2.setText(bundle.getString("strName"));
        mBinding.tvDesignPayment2.setText(bundle.getString("strPayment"));
        mBinding.tvDesignState2.setText(bundle.getString("strState"));



    }

    private void init() {
              initSpinner();

        mBinding.buttonInsuranceDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mBinding.tvDesignId2.getText().toString();
                String acquisition = mBinding.tvDesignAcquisition2.getText().toString();
                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .designDriverInsurance(new DriverDesignInsuranceRequest(Long.valueOf(id), acquisition, Constant.DriverLicence.valueOf(strLicense)))
                        .enqueue(new Callback<DriverDesignInsuranceResponse>() {
                            @Override
                            public void onResponse(Call<DriverDesignInsuranceResponse> call, Response<DriverDesignInsuranceResponse> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(fragmentContext, "보험 설계를 완료했습니다.", Toast.LENGTH_SHORT).show();
                                    replaceFragment(HomeFragment.newInstance());
                                } else{
                                }
                            }
                            @Override
                            public void onFailure(Call<DriverDesignInsuranceResponse> call, Throwable t) {
                            }
                        });
            }
        });

    }

    public void initSpinner(){
        strLicense = "TYPE_1_NORMAL";
        mBinding.spinnerLicenseSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("1종 보통")){
                    strLicense = "TYPE_1_NORMAL";
                } else if(adapterView.getItemAtPosition(i).equals("2종 보통")){
                    strLicense = "TYPE_2_NORMAL";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbDesignDriverInsurance);
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
                replaceFragment(DesignInsuranceSecondFragment.newInstance());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static DesignDriverInsuranceFragment newInstance() {
        return new DesignDriverInsuranceFragment();
    }
}