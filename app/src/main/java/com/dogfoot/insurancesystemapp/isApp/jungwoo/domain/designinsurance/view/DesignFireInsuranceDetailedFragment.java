package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view;

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
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignInsuranceFireDetailedBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view.PlanInsuranceFirstFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignFireInsuranceDetailedFragment extends DogFootViewModelFragment {

    private FragmentDesignInsuranceFireDetailedBinding mBinding;
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
        mBinding = FragmentDesignInsuranceFireDetailedBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        mBinding.tvDesignId3.setText(bundle.getString("strId"));
        mBinding.tvDesignName3.setText(bundle.getString("strName"));
        mBinding.tvDesignPayment3.setText(bundle.getString("strPayment"));
        mBinding.tvDesignState3.setText(bundle.getString("strState"));

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token)
                .getFireInsuracneDetailed(Integer.parseInt(bundle.getString("strId"))).enqueue(new Callback<FireDesignInsuranceResponse>() {
            @Override
            public void onResponse(Call<FireDesignInsuranceResponse> call,
                                   Response<FireDesignInsuranceResponse> response) {
                if(response.isSuccessful()){
                    mBinding.tvDesignPrice3.setText(response.body().getBuilding_price());
                    mBinding.tvDesignDate3.setText(response.body().getConstruction_date());
                    mBinding.tvDesignsite3.setText(response.body().getSite_area());
                    mBinding.tvDesignFloors3.setText(response.body().getNumber_of_floors());
                } else{
                }
            }
            @Override
            public void onFailure(Call<FireDesignInsuranceResponse> call, Throwable t) {
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbDesignFireInsuranceToolbar);
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
                replaceFragment(DesignInsuranceFirstFragment.newInstance());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static DesignFireInsuranceDetailedFragment newInstance() {
        return new DesignFireInsuranceDetailedFragment();
    }
}