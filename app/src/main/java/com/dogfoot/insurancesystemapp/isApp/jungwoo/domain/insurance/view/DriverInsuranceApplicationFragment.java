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
import com.dogfoot.insurancesystemapp.databinding.FragmentCarInsuranceApplicationBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignInsuranceCarDetailedBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.approveinsurance.view.ApproveInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.authorizeinsurance.view.AuthorizeInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyThirdFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.PaymentResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceResponse;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverInsuranceApplicationFragment extends DogFootViewModelFragment {

    private FragmentCarInsuranceApplicationBinding mBinding;
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
        mBinding = FragmentCarInsuranceApplicationBinding.inflate(getLayoutInflater());
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
        mBinding.tvCarInsuranceApplicationId.setText(id);

        mBinding.buttonCarInsuranceApplicationDetailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("strId", id);
                FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .getPossibleCarInsuranceDetailed(Long.valueOf(id))
                        .enqueue(new Callback<CarInsuranceResponse>() {
                            @Override
                            public void onResponse(Call<CarInsuranceResponse> call,
                                                   Response<CarInsuranceResponse> response) {
                                if (response.isSuccessful()) {
                                    bundle.putString("strName", response.body().getName());
                                    bundle.putString("strPayment", String.valueOf(response.body().getPayment()));
                                    bundle.putString("strPhysical", response.body().getPhysical());
                                    bundle.putString("strEconomical", response.body().getEconomical());
                                    bundle.putString("strEnvironmental", response.body().getEnvironmental());
                                    bundle.putString("strPrice", String.valueOf(response.body().getCar_price()));
                                    bundle.putString("strRelease", response.body().getCar_release_date());
                                    bundle.putString("strDistance", String.valueOf(response.body().getDriving_distance()));
                                    CarInsuranceApplicationDetailedFragment carInsuranceApplicationDetailedFragment = CarInsuranceApplicationDetailedFragment.newInstance();
                                    carInsuranceApplicationDetailedFragment.setArguments(bundle);
                                    fragmentTransaction.replace(R.id.fl_main, carInsuranceApplicationDetailedFragment).commit();
                                } else {
                                }
                            }
                            @Override
                            public void onFailure(Call<CarInsuranceResponse> call, Throwable t) {
                            }
                        });
            }
        });

        mBinding.buttonCarInsuranceApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String physical = mBinding.etCarInsuranceApplicationPhysical.getText().toString();
                String economical = mBinding.etCarInsuranceApplicationEconomical.getText().toString();
                String environmental = mBinding.etCarInsuranceApplicationEnvironmental.getText().toString();
                String price = mBinding.etCarInsuranceApplicationPrice.getText().toString();
                String releaseDate = mBinding.etCarInsuranceApplicationReleaseDate.getText().toString();
                String distance = mBinding.etCarInsuranceApplicationDistance.getText().toString();
                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .applyForCarInsurance(new CarInsuranceRequest(Long.valueOf(id), physical, economical, environmental, Long.valueOf(price), releaseDate, Long.valueOf(distance)))
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
                String physical = mBinding.etCarInsuranceApplicationPhysical.getText().toString();
                String economical = mBinding.etCarInsuranceApplicationEconomical.getText().toString();
                String environmental = mBinding.etCarInsuranceApplicationEnvironmental.getText().toString();
                String price = mBinding.etCarInsuranceApplicationPrice.getText().toString();
                String releaseDate = mBinding.etCarInsuranceApplicationReleaseDate.getText().toString();
                String distance = mBinding.etCarInsuranceApplicationDistance.getText().toString();
                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .calculateCarInsurancePrice(new CarInsuranceRequest(Long.valueOf(id), physical, economical, environmental,
                                Long.valueOf(price), releaseDate, Long.valueOf(distance)))
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

    public static DriverInsuranceApplicationFragment newInstance() {
        return new DriverInsuranceApplicationFragment();
    }
}