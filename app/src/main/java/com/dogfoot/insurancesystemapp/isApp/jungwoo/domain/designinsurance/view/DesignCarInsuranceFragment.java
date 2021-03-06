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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignCarInsuranceBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignCarInsuranceFragment extends DogFootViewModelFragment {

    private FragmentDesignCarInsuranceBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDesignCarInsuranceBinding.inflate(getLayoutInflater());
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
        mBinding.tvDesignId.setText(bundle.getString("strId"));
        mBinding.tvDesignName.setText(bundle.getString("strName"));
        mBinding.tvDesignPayment.setText(bundle.getString("strPayment"));
        mBinding.tvDesignState.setText(bundle.getString("strState"));

    }

    private void init() {
        mBinding.buttonInsuranceDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mBinding.tvDesignId.getText().toString();
                String price = mBinding.tvDesignPrice.getText().toString();
                String release = calculateDate();
                String distance = mBinding.tvDesignDistance.getText().toString();
                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .designCarInsurance(new CarDesignInsuranceRequest(Long.valueOf(id), Long.valueOf(price), release, Long.valueOf(distance)))
                        .enqueue(new Callback<CarDesignInsuranceResponse>() {
                            @Override
                            public void onResponse(Call<CarDesignInsuranceResponse> call, Response<CarDesignInsuranceResponse> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(fragmentContext, "?????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                                    replaceFragment(HomeFragment.newInstance());
                                } else{
                                }
                            }
                            @Override
                            public void onFailure(Call<CarDesignInsuranceResponse> call, Throwable t) { }
                        });
            }
        });

    }
    private String calculateDate() {

        String year;
        String month;
        String day;
        year = Integer.toString(mBinding.dpCarInsuranceRelease.getYear());
        if(mBinding.dpCarInsuranceRelease.getMonth()+1<10){
            month = "0"+Integer.toString(mBinding.dpCarInsuranceRelease.getMonth()+1);
        } else {
            month = Integer.toString(mBinding.dpCarInsuranceRelease.getMonth()+1);
        }
        if(mBinding.dpCarInsuranceRelease.getDayOfMonth()<10){
            day = "0"+Integer.toString(mBinding.dpCarInsuranceRelease.getDayOfMonth());
        } else {
            day = Integer.toString(mBinding.dpCarInsuranceRelease.getDayOfMonth());
        }
        String date = year+"-"
                +month+"-"
                +day;
        return date;
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbDesignCarInsuranceToolbar);
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

    public static DesignCarInsuranceFragment newInstance() {
        return new DesignCarInsuranceFragment();
    }
}