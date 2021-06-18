package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicyFirstBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.adapter.CapacityPolicyAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyResponse;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationCapacityPolicyFirstFragment extends DogFootViewModelFragment {

    private FragmentRegistrationCapacityPolicyFirstBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    private CapacityPolicyAdapter capacityPolicyAdapter;
    private RecyclerView capacityPolicyRecyclerView;
    private LinearLayoutManager capacityPolicyLayoutManager;

    Vector<CapacityPolicyResponse> capacityPolicyList;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentRegistrationCapacityPolicyFirstBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        init();

        return view;
    }

    private void init() {
        capacityPolicyList = new Vector<>();
        capacityPolicyRecyclerView = mBinding.rvCapacityPolicyList;
        capacityPolicyLayoutManager = new LinearLayoutManager(context);
        capacityPolicyRecyclerView.setLayoutManager(capacityPolicyLayoutManager);
        capacityPolicyAdapter = new CapacityPolicyAdapter(context, fragmentContext);
        capacityPolicyRecyclerView.setAdapter(capacityPolicyAdapter);

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token).getCapacityPolicyList()
                .enqueue(new Callback<Pagination<List<CapacityPolicyResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<CapacityPolicyResponse>>> call,
                                           Response<Pagination<List<CapacityPolicyResponse>>> response) {
                        if(response.isSuccessful()){

                            for(CapacityPolicyResponse res: response.body().getData()){
                                capacityPolicyList.add(res);
                            }
                            capacityPolicyAdapter.addCarItems(capacityPolicyList);
                            capacityPolicyAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }
                    @Override
                    public void onFailure(Call<Pagination<List<CapacityPolicyResponse>>> call, Throwable t) { }
                });

        mBinding.btnCapacityPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(RegistrationCapacityPolicySecondFragment.newInstance());
            }
        });
    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbCapacityPolicyToolbar);
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

    public static RegistrationCapacityPolicyFirstFragment newInstance() {
        return new RegistrationCapacityPolicyFirstFragment();
    }

    @Override
    protected int getLayoutId() { return 0; }
    @Override
    protected void associateView(View view) { }
    @Override
    protected void initializeView() { }
    @Override
    protected void dogFootEntityUpdated() { }
}