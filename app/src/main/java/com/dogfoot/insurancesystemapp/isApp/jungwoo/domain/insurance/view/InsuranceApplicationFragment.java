package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view;

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
import com.dogfoot.insurancesystemapp.databinding.FragmentInsuranceApplicationBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicySecondBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
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

public class InsuranceApplicationFragment extends DogFootViewModelFragment {

    private FragmentInsuranceApplicationBinding mBinding;
    private CarInsuranceAdapter carInsuranceAdapter;
    private DriverInsuranceAdapter driverInsuranceAdapter;
    private FireInsuranceAdapter fireInsuranceAdapter;
    private TravelInsuranceAdapter travelInsuranceAdapter;
    private RecyclerView carRecyclerView;
    private RecyclerView driverRecyclerView;
    private RecyclerView fireRecyclerView;
    private RecyclerView travelRecyclerView;
    private LinearLayoutManager carLinearLayoutManager;
    private LinearLayoutManager driverLinearLayoutManager;
    private LinearLayoutManager fireLinearLayoutManager;
    private LinearLayoutManager travelLinearLayoutManager;

    private Context context;
    private FragmentActivity fragmentContext;

    Vector<CarInsuranceResponse> carItems;
    Vector<DriverInsuranceResponse> driverItems;
    Vector<FireInsuranceResponse> fireItems;
    Vector<TravelInsuranceResponse> travelItems;


    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DesignCarInsuranceDetailedFragment.setBackCheckAuthorize(false);

        mBinding = FragmentInsuranceApplicationBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        init();

        carRecyclerView = mBinding.rvPossibleCarInsuranceApplication;
        driverRecyclerView = mBinding.rvPossibleDriverInsuranceApplication;
        fireRecyclerView = mBinding.rvPossibleFireInsuranceApplication;
        travelRecyclerView = mBinding.rvPossibleTravelInsuranceApplication;
        carLinearLayoutManager = new LinearLayoutManager(context);
        driverLinearLayoutManager = new LinearLayoutManager(context);
        fireLinearLayoutManager = new LinearLayoutManager(context);
        travelLinearLayoutManager = new LinearLayoutManager(context);

        carRecyclerView.setLayoutManager(carLinearLayoutManager);
        carInsuranceAdapter = new CarInsuranceAdapter(context, fragmentContext, false, true);
        carRecyclerView.setAdapter(carInsuranceAdapter);

        driverRecyclerView.setLayoutManager(driverLinearLayoutManager);
        driverInsuranceAdapter = new DriverInsuranceAdapter(context, fragmentContext, false, true);
        driverRecyclerView.setAdapter(driverInsuranceAdapter);

        fireRecyclerView.setLayoutManager(fireLinearLayoutManager);
        fireInsuranceAdapter = new FireInsuranceAdapter(context, fragmentContext, false, true);
        fireRecyclerView.setAdapter(fireInsuranceAdapter);

        travelRecyclerView.setLayoutManager(travelLinearLayoutManager);
        travelInsuranceAdapter = new TravelInsuranceAdapter(context, fragmentContext, false, true);
        travelRecyclerView.setAdapter(travelInsuranceAdapter);

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        carItems = new Vector<>();
        driverItems = new Vector<>();
        fireItems = new Vector<>();
        travelItems = new Vector<>();

        RetrofitTool.getAPIWithAuthorizationToken(token).getPossibleCarInsuranceList()
                .enqueue(new Callback<Pagination<List<CarInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<CarInsuranceResponse>>> call,
                                           Response<Pagination<List<CarInsuranceResponse>>> response) {
                        if(response.isSuccessful()){

                            for(CarInsuranceResponse res: response.body().getData()){
                                carItems.add(res);
                            }
                            carInsuranceAdapter.addCarItems(carItems);
                            carInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<CarInsuranceResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).getPossibleDriverInsuranceList()
                .enqueue(new Callback<Pagination<List<DriverInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<DriverInsuranceResponse>>> call,
                                           Response<Pagination<List<DriverInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            driverItems = new Vector<>();
                            for(DriverInsuranceResponse res: response.body().getData()){
                                driverItems.add(res);
                            }
                            driverInsuranceAdapter.addDriverItems(driverItems);
                            driverInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }
                    @Override
                    public void onFailure(Call<Pagination<List<DriverInsuranceResponse>>> call, Throwable t) {
                    }
                });

        RetrofitTool.getAPIWithAuthorizationToken(token).getPossibleFireInsuranceList()
                .enqueue(new Callback<Pagination<List<FireInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<FireInsuranceResponse>>> call,
                                           Response<Pagination<List<FireInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            fireItems = new Vector<>();
                            for(FireInsuranceResponse res: response.body().getData()){
                                fireItems.add(res);
                            }
                            fireInsuranceAdapter.addFireItems(fireItems);
                            fireInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }
                    @Override
                    public void onFailure(Call<Pagination<List<FireInsuranceResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).getPossibleTravelInsuranceList()
                .enqueue(new Callback<Pagination<List<TravelInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<TravelInsuranceResponse>>> call,
                                           Response<Pagination<List<TravelInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            travelItems = new Vector<>();
                            for(TravelInsuranceResponse res: response.body().getData()){
                                travelItems.add(res);
                            }
                            travelInsuranceAdapter.addTravelItems(travelItems);
                            travelInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<TravelInsuranceResponse>>> call, Throwable t) {
                    }
                });


        return view;
    }

    private void init() {
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbInsuranceApplicationToolbar);
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

    public static InsuranceApplicationFragment newInstance() {
        return new InsuranceApplicationFragment();
    }
}