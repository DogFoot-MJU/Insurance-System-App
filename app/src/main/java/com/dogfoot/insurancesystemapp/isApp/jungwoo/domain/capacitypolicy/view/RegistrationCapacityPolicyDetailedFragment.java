package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view;

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

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicyDetailedBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationCapacityPolicyDetailedFragment extends DogFootViewModelFragment {

    private FragmentRegistrationCapacityPolicyDetailedBinding mBinding;
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
        mBinding = FragmentRegistrationCapacityPolicyDetailedBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        String id = bundle.getString("strId");
        String insuranceName = bundle.getString("strInsuranceName");
        mBinding.tvCapacityPolicyIdDetailed.setText(id);
        mBinding.tvCapacityPolicyNameDetailed.setText(bundle.getString("strPolicyName"));
        mBinding.tvInsuranceNameDetailed.setText(insuranceName);

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token)
                .getCapacityPolicyDetailed(Long.valueOf(bundle.getString("strId"))).enqueue(new Callback<CapacityPolicyResponse>() {
            @Override
            public void onResponse(Call<CapacityPolicyResponse> call,
                                   Response<CapacityPolicyResponse> response) {
                if (response.isSuccessful()) {
                    mBinding.tvPhysicalDetailed.setText(String.valueOf(response.body().getPhysical()));
                    mBinding.tvEconomicalDetailed.setText(response.body().getEconomical());
                    mBinding.tvEnvironmentalDetailed.setText(String.valueOf(response.body().getEnvironmental()));
                } else {
                }
            }
            @Override
            public void onFailure(Call<CapacityPolicyResponse> call, Throwable t) { }
        });

        mBinding.btnUpdateCapacityPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle updateBundle = new Bundle();
                updateBundle.putString("strId", id);
                updateBundle.putString("strInsuranceName", insuranceName);
                FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RegistrationCapacityPolicyUpdateFragment registrationCapacityPolicyUpdateFragment = RegistrationCapacityPolicyUpdateFragment.newInstance();
                registrationCapacityPolicyUpdateFragment.setArguments(updateBundle);
                fragmentTransaction.replace(R.id.fl_main, registrationCapacityPolicyUpdateFragment).commit();
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbCapacityPolicyDetailedToolbar);
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
        if(item.getItemId()==android.R.id.home){
                replaceFragment(RegistrationCapacityPolicyFirstFragment.newInstance());
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static RegistrationCapacityPolicyDetailedFragment newInstance() {
        return new RegistrationCapacityPolicyDetailedFragment();
    }
}