package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view;

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
import com.dogfoot.insurancesystemapp.databinding.FragmentDriverInsuranceApplicationBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentFireInsuranceApplicationBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentTravelInsuranceApplicationBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.PaymentResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.DriverInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.DriverInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.FireInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.FireInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.TravelInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.TravelInsuranceResponse;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelInsuranceApplicationFragment extends DogFootViewModelFragment {

    private FragmentTravelInsuranceApplicationBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    @Getter
    @Setter
    private static Boolean backCheckAuthorize;
    @Getter
    @Setter
    private static Boolean backCheckApprove;
    String id;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentTravelInsuranceApplicationBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        id = bundle.getString("strId");
        mBinding.tvTravelInsuranceApplicationId.setText(id);

        mBinding.buttonTravelInsuranceApplicationDetailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("strId", id);
                FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .getPossibleTravelInsuranceDetailed(Long.valueOf(id))
                        .enqueue(new Callback<TravelInsuranceResponse>() {
                            @Override
                            public void onResponse(Call<TravelInsuranceResponse> call,
                                                   Response<TravelInsuranceResponse> response) {
                                if (response.isSuccessful()) {
                                    bundle.putString("strName", response.body().getName());
                                    bundle.putString("strPayment", String.valueOf(response.body().getPayment()));
                                    bundle.putString("strPhysical", response.body().getPhysical());
                                    bundle.putString("strEconomical", response.body().getEconomical());
                                    bundle.putString("strEnvironmental", response.body().getEnvironmental());
                                    bundle.putString("strSafetyRank", String.valueOf(response.body().getSafetyRank()));
                                    TravelInsuranceApplicationDetailedFragment travelInsuranceApplicationDetailedFragment = TravelInsuranceApplicationDetailedFragment.newInstance();
                                    travelInsuranceApplicationDetailedFragment.setArguments(bundle);
                                    fragmentTransaction.replace(R.id.fl_main, travelInsuranceApplicationDetailedFragment).commit();
                                } else {
                                }
                            }
                            @Override
                            public void onFailure(Call<TravelInsuranceResponse> call, Throwable t) {
                            }
                        });
            }
        });

        mBinding.buttonTravelInsuranceApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String physical = mBinding.tvTravelInsuranceApplicationPhysical.getText().toString();
                String economical = mBinding.tvTravelInsuranceApplicationEconomical.getText().toString();
                String environmental = mBinding.tvTravelInsuranceApplicationEnvironmental.getText().toString();
                String safetyRank = mBinding.tvTravelInsuranceApplicationSafetyRank.getText().toString();

                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .applyForTravelInsurance(new TravelInsuranceRequest(Long.valueOf(id), physical, economical, environmental,
                                Constant.SafetyRank.valueOf(safetyRank)))
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call,
                                                   Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(fragmentContext, "보험 신청을 완료했습니다.", Toast.LENGTH_SHORT).show();
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

        mBinding.btnRateCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String physical = mBinding.tvTravelInsuranceApplicationPhysical.getText().toString();
                String economical = mBinding.tvTravelInsuranceApplicationEconomical.getText().toString();
                String environmental = mBinding.tvTravelInsuranceApplicationEnvironmental.getText().toString();
                String safetyRank = mBinding.tvTravelInsuranceApplicationSafetyRank.getText().toString();

                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .calculateTravelInsurancePrice(new TravelInsuranceRequest(Long.valueOf(id), physical, economical, environmental,
                                Constant.SafetyRank.valueOf(safetyRank)))
                        .enqueue(new Callback<PaymentResponse>() {
                            @Override
                            public void onResponse(Call<PaymentResponse> call,
                                                   Response<PaymentResponse> response) {
                                if (response.isSuccessful()) {
                                    mBinding.tvRateCalculated.setText(response.body().getPayment().toString());
                                    Toast.makeText(fragmentContext, "요율 계산을 완료했습니다.", Toast.LENGTH_SHORT).show();
                                } else {
                                }
                            }
                            @Override
                            public void onFailure(Call<PaymentResponse> call, Throwable t) {
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
        if(item.getItemId()==android.R.id.home){
            replaceFragment(InsuranceApplicationFragment.newInstance());
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static TravelInsuranceApplicationFragment newInstance() {
        return new TravelInsuranceApplicationFragment();
    }
}