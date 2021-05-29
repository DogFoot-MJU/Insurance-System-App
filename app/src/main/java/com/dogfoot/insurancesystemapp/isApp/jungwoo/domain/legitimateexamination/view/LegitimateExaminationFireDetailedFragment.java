package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignInsuranceCarDetailedBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentLegitimateExaminationCarDetailedBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentLegitimateExaminationFireDetailedBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.approveinsurance.view.ApproveInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.authorizeinsurance.view.AuthorizeInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.CarLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.FireLegitimateExaminationResponse;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LegitimateExaminationFireDetailedFragment extends DogFootViewModelFragment {

    private FragmentLegitimateExaminationFireDetailedBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    private String id;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentLegitimateExaminationFireDetailedBinding.inflate(getLayoutInflater());
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
        id = bundle.getString("strId");
        mBinding.tvLegitimateId.setText(id);

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token)
                .getFireLegitimateExaminationDetailed(Long.valueOf(id)).enqueue(new Callback<FireLegitimateExaminationResponse>() {
            @Override
            public void onResponse(Call<FireLegitimateExaminationResponse> call,
                                   Response<FireLegitimateExaminationResponse> response) {
                if (response.isSuccessful()) {
                    mBinding.tvLegitimateInsuranceName.setText(response.body().getInsurance_name());
                    mBinding.tvLegitimateUserName.setText(response.body().getUser_name());
                    mBinding.tvLegitimateEmail.setText(response.body().getEmail());
                    mBinding.tvLegitimatePhysical.setText(response.body().getCustomer_physical());
                    mBinding.tvLegitimateEconomical.setText(response.body().getCustomer_economical());
                    mBinding.tvLegitimateEnvironmental.setText(response.body().getCustomer_environmental());
                    mBinding.tvLegitimateRate.setText(String.valueOf(response.body().getCalculated_payment()));
                    mBinding.tvLegitimateExpirationDate.setText(response.body().getExpiration_date());
                    mBinding.tvLegitimateUWProcess.setText(String.valueOf(response.body().getUw_due_process_type()));
                    mBinding.tvLegitimateCustomerBuildingPrice.setText(String.valueOf(response.body().getBuilding_price()));
                    mBinding.tvLegitimateCustomerConstructionDate.setText(String.valueOf(response.body().getConstruction_date()));
                    mBinding.tvLegitimateCustomerSiteArea.setText(String.valueOf(response.body().getSite_area()));
                    mBinding.tvLegitimateCustomerFloors.setText(String.valueOf(response.body().getNumber_of_floors()));
                } else {
                }
            }

            @Override
            public void onFailure(Call<FireLegitimateExaminationResponse> call, Throwable t) {
            }
        });
    }

    private void init() {
        mBinding.btnContractApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .approveLegitimateExaminationFire(Long.valueOf(id)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call,
                                           Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(fragmentContext, "계약 승인이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            replaceFragment(HomeFragment.newInstance());
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
        });

        mBinding.btnContractReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .rejectLegitimateExaminationFire(Long.valueOf(id)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call,
                                           Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(fragmentContext, "계약 거절이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            replaceFragment(HomeFragment.newInstance());
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbLegitimateExaminationFireToolbar);
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
            replaceFragment(LegitimateExaminationFirstFragment.newInstance());

        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static LegitimateExaminationFireDetailedFragment newInstance() {
        return new LegitimateExaminationFireDetailedFragment();
    }
}