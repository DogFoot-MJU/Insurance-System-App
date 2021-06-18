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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentCarInsurnaceApplicationDetailedBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignInsuranceCarDetailedBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.approveinsurance.view.ApproveInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.authorizeinsurance.view.AuthorizeInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceResponse;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarInsuranceApplicationDetailedFragment extends DogFootViewModelFragment {

    private FragmentCarInsurnaceApplicationDetailedBinding mBinding;
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

    private String id;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCarInsurnaceApplicationDetailedBinding.inflate(getLayoutInflater());
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
        mBinding.tvCarInsuranceDetailedId.setText(id);
        mBinding.tvCarInsuranceDetailedName.setText(bundle.getString("strName"));
        mBinding.tvCarInsuranceDetailedPayment.setText(bundle.getString("strPayment"));
        mBinding.tvCarInsuranceApplicationPhysical.setText(String.valueOf(bundle.get("strPhysical")));
        mBinding.tvCarInsuranceApplicationEconomical.setText(String.valueOf(bundle.get("strEconomical")));
        mBinding.tvCarInsuranceApplicationEnvironmental.setText(bundle.getString("strEnvironmental"));
        mBinding.tvCarInsuranceApplicationPrice.setText(String.valueOf(bundle.get("strPrice")));
        mBinding.tvCarInsuranceApplicationRelease.setText(String.valueOf(bundle.get("strRelease")));
        mBinding.tvCarInsuranceApplicationDistance.setText(bundle.getString("strDistance"));

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
            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("strId", id);
            CarInsuranceApplicationFragment carInsuranceApplicationFragment = CarInsuranceApplicationFragment.newInstance();
            carInsuranceApplicationFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_main, carInsuranceApplicationFragment).commit();
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