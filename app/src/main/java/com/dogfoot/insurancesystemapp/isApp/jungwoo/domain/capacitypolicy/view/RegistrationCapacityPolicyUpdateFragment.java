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
import android.widget.Toast;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicyDetailedBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicyUpdateBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyUpdateRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationCapacityPolicyUpdateFragment extends DogFootViewModelFragment {

    private FragmentRegistrationCapacityPolicyUpdateBinding mBinding;
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
        mBinding = FragmentRegistrationCapacityPolicyUpdateBinding.inflate(getLayoutInflater());
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
        mBinding.tvCapacityPolicyIdUpdate.setText(id);
        mBinding.tvCapacityPolicyInsuranceNameUpdate.setText(insuranceName);

        mBinding.btnUpdateCapacityPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mBinding.etCapacityPolicyNameUpdate.getText().toString();
                String physical = mBinding.etCapacityPolicyPhysicalUpdate.getText().toString();
                String economical = mBinding.etCapacityPolicyEconomicalUpdate.getText().toString();
                String environmental = mBinding.etCapacityPolicyEnvironmentalUpdate.getText().toString();

                Constant constant = Constant.getInstance();
                String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
                RetrofitTool.getAPIWithAuthorizationToken(token)
                        .updateCapacityPolicy(new CapacityPolicyUpdateRequest(Long.valueOf(id), name, physical, economical, environmental))
                        .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call,
                                           Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(fragmentContext, "인수정책 수정을 완료했습니다.", Toast.LENGTH_SHORT).show();
                            replaceFragment(HomeFragment.newInstance());
                        } else {
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) { }
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

    public static RegistrationCapacityPolicyUpdateFragment newInstance() {
        return new RegistrationCapacityPolicyUpdateFragment();
    }
}