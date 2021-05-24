package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view;

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
import com.dogfoot.insurancesystemapp.databinding.FragmentCarInsurnaceApplicationDetailedBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentDriverInsuranceApplicationDetailedBinding;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;

import lombok.Getter;
import lombok.Setter;

public class DriverInsuranceApplicationDetailedFragment extends DogFootViewModelFragment {

    private FragmentDriverInsuranceApplicationDetailedBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    private String authorizeBack;
    private String approveBack;
    @Getter
    @Setter
    private static Boolean backCheckAuthorize;
    @Getter
    @Setter
    private static Boolean backCheckApprove;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDriverInsuranceApplicationDetailedBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
//        mBinding.tvCarInsuranceDetailedId.setText(bundle.getString("strId"));
//        mBinding.tvCarInsuranceDetailedName.setText(bundle.getString("strName"));
//        mBinding.tvCarInsuranceDetailedPayment.setText(bundle.getString("strPayment"));
//        mBinding.tvCarInsuranceApplicationPhysical.setText(String.valueOf(bundle.get("physical")));
//        mBinding.tvCarInsuranceApplicationEconomical.setText(String.valueOf(bundle.get("economical")));
//        mBinding.tvCarInsuranceApplicationEnvironmental.setText(bundle.getString("environmental"));
//        mBinding.tvCarInsuranceApplicationPhysical.setText(String.valueOf(bundle.get("price")));
//        mBinding.tvCarInsuranceApplicationEconomical.setText(String.valueOf(bundle.get("release")));
//        mBinding.tvCarInsuranceApplicationEnvironmental.setText(bundle.getString("distance"));

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
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbDesignCarInsuranceToolbar);
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
            replaceFragment(CarInsuranceApplicationFragment.newInstance());
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    public static CarInsuranceApplicationDetailedFragment newInstance() {
        return new CarInsuranceApplicationDetailedFragment();
    }
}