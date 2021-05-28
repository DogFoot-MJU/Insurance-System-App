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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignInsuranceFireDetailedBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignInsuranceTravelDetailedBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.approveinsurance.view.ApproveInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.authorizeinsurance.view.AuthorizeInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view.PlanInsuranceFirstFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignTravelInsuranceDetailedFragment extends DogFootViewModelFragment {

    private FragmentDesignInsuranceTravelDetailedBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    private String authorizeBack;
    private String approveBack;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDesignInsuranceTravelDetailedBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        mBinding.tvDesignId4.setText(bundle.getString("strId"));
        mBinding.tvDesignName4.setText(bundle.getString("strName"));
        mBinding.tvDesignPayment4.setText(bundle.getString("strPayment"));
        mBinding.tvDesignState4.setText(bundle.getString("strState"));
        mBinding.textView10.setText(bundle.getString("integerExpirationDate"));
        authorizeBack = bundle.getString("authorize");
        approveBack = bundle.getString("approve");

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token)
                .getTravelInsuracneDetailed(Long.valueOf(bundle.getString("strId"))).enqueue(new Callback<TravelDesignInsuranceResponse>() {
            @Override
            public void onResponse(Call<TravelDesignInsuranceResponse> call,
                                   Response<TravelDesignInsuranceResponse> response) {
                if(response.isSuccessful()){
                    mBinding.textView10.setText(String.valueOf(response.body().getExpiration_date()));
                    mBinding.tvDesignSafe4.setText(String.valueOf(response.body().getSafety_rank()));
                } else{
                }
            }
            @Override
            public void onFailure(Call<TravelDesignInsuranceResponse> call, Throwable t) {
            }
        });

        if(authorizeBack.equals("true")){
            mBinding.buttonAuthorizeNApprove.setVisibility(View.VISIBLE);
            mBinding.buttonAuthorizeNApprove.setText("보험 상품 인가하기");
            mBinding.tvTravelAuthorizeNApproveInfo.setText("선택한 보험 상품의 상세한 내용입니다.");
            mBinding.tvTravelAuthorizeNApproveToolbarInfo.setText("보험 상품 인가하기");
            mBinding.buttonAuthorizeNApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RetrofitTool.getAPIWithAuthorizationToken(token).authorizeTravelInsurance(Long.valueOf(bundle.getString("strId")))
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.isSuccessful()){
                                        Toast.makeText(fragmentContext, "상품 인가 완료했습니다.", Toast.LENGTH_SHORT).show(); }
                                    replaceFragment(HomeFragment.newInstance());}
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) { }
                            });
                }
            });
        } else if(approveBack.equals("true")){
            mBinding.buttonAuthorizeNApprove.setVisibility(View.VISIBLE);
            mBinding.buttonAuthorizeNApprove.setText("보험 상품 승인하기");
            mBinding.tvTravelAuthorizeNApproveInfo.setText("선택한 보험 상품의 상세한 내용입니다.");
            mBinding.tvTravelAuthorizeNApproveToolbarInfo.setText("보험 상품 승인하기");
            mBinding.buttonAuthorizeNApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RetrofitTool.getAPIWithAuthorizationToken(token).approveTravelInsurance(Long.valueOf(bundle.getString("strId")))
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.isSuccessful()){
                                        Toast.makeText(fragmentContext, "상품 승인 완료했습니다.", Toast.LENGTH_SHORT).show();
                                        replaceFragment(HomeFragment.newInstance());} }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) { }
                            });
                }
            });
        }
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbDesignTravelInsuranceToolbar);
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
            if(authorizeBack.equals("true")){
                replaceFragment(AuthorizeInsuranceFirstFragment.newInstance());
            } else if(approveBack.equals("true")){
                replaceFragment(ApproveInsuranceFirstFragment.newInstance());
            } else{
                replaceFragment(DesignInsuranceFirstFragment.newInstance());
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static DesignTravelInsuranceDetailedFragment newInstance() {
        return new DesignTravelInsuranceDetailedFragment();
    }
}