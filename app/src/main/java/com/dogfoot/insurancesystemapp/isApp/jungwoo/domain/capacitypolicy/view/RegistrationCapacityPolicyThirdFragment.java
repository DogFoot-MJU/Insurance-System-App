package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicySecondBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicyThirdBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignCarInsuranceDetailedFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.adapter.CarInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.adapter.DriverInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.adapter.FireInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.adapter.TravelInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.DriverInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.FireInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.TravelInsuranceResponse;

import java.util.List;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationCapacityPolicyThirdFragment extends DogFootViewModelFragment {

    private FragmentRegistrationCapacityPolicyThirdBinding mBinding;

    private Context context;
    private FragmentActivity fragmentContext;

    @Setter
    @Getter
    private String token;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DesignCarInsuranceDetailedFragment.setBackCheckAuthorize(false);

        mBinding = FragmentRegistrationCapacityPolicyThirdBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();

        Bundle bundle = getArguments();
        String id = bundle.getString("strId");
        String insuranceName = bundle.getString("strInsuranceName");
        mBinding.tvCapacityPolicyIdUpdate.setText(id);
        mBinding.tvCapacityPolicyInsuranceNameUpdate.setText(insuranceName);

        mBinding.btnRegisterCapacityPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);

                String insuranceName = mBinding.etCapacityPolicyNameUpdate.getText().toString();
                String physical = mBinding.etCapacityPolicyPhysicalUpdate.getText().toString();
                String economical = mBinding.etCapacityPolicyEconomicalUpdate.getText().toString();
                String environmental = mBinding.etCapacityPolicyEnvironmentalUpdate.getText().toString();

                RetrofitTool.getAPIWithAuthorizationToken(token).registrationCapacityPolicy(new CapacityPolicyRequest(Long.valueOf(id), insuranceName,
                        physical, economical, environmental))
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call,
                                                   Response<Void> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(fragmentContext, "인수정책 등록을 완료했습니다.", Toast.LENGTH_SHORT).show();
                                    replaceFragment(HomeFragment.newInstance());
                                } else{
                                }
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }
                        });
            }
        });

        return view;
    }

    private String getToken(){
        return token;
    }

    private void setToken(String token) {
        this.token = token;
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }
    @Override
    protected void associateView(View view) { }
    @Override
    protected void initializeView() { }
    @Override
    protected void dogFootEntityUpdated() { }

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

    public static RegistrationCapacityPolicyThirdFragment newInstance() {
        return new RegistrationCapacityPolicyThirdFragment();
    }
}