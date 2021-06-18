package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import com.dogfoot.insurancesystemapp.databinding.FragmentDesignInsuranceFirstBinding;
import com.dogfoot.insurancesystemapp.databinding.FragmentLegitimateExaminationFirstBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.CarDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.DriverDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.FireDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.TravelDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.adpater.CarLegitimateExaminationAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.adpater.DriverLegitimateExaminationAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.adpater.FireLegitimateExaminationAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.adpater.TravelLegitimateExaminationAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.CarLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.DriverLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.FireLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.TravelLegitimateExaminationResponse;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LegitimateExaminationFirstFragment extends DogFootViewModelFragment {

    private FragmentLegitimateExaminationFirstBinding mBinding;
    private CarLegitimateExaminationAdapter carLegitimateExaminationAdapter;
    private DriverLegitimateExaminationAdapter driverLegitimateExaminationAdapter;
    private FireLegitimateExaminationAdapter fireLegitimateExaminationAdapter;
    private TravelLegitimateExaminationAdapter travelLegitimateExaminationAdapter;
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

    Vector<CarLegitimateExaminationResponse> carItems;
    Vector<DriverLegitimateExaminationResponse> driverItems;
    Vector<FireLegitimateExaminationResponse> fireItems;
    Vector<TravelLegitimateExaminationResponse> travelItems;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentLegitimateExaminationFirstBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        init();

        carRecyclerView = mBinding.rvCarLegitimateExaminationList;
        driverRecyclerView = mBinding.rvDriverLegitimateExaminationList;
        fireRecyclerView = mBinding.rvFireLegitimateExaminationList;
        travelRecyclerView = mBinding.rvTravelLegitimateExaminationList;
        carLinearLayoutManager = new LinearLayoutManager(context);
        driverLinearLayoutManager = new LinearLayoutManager(context);
        fireLinearLayoutManager = new LinearLayoutManager(context);
        travelLinearLayoutManager = new LinearLayoutManager(context);

        carRecyclerView.setLayoutManager(carLinearLayoutManager);
        carLegitimateExaminationAdapter = new CarLegitimateExaminationAdapter(context, fragmentContext);
        carRecyclerView.setAdapter(carLegitimateExaminationAdapter);

        driverRecyclerView.setLayoutManager(driverLinearLayoutManager);
        driverLegitimateExaminationAdapter = new DriverLegitimateExaminationAdapter(context, fragmentContext);
        driverRecyclerView.setAdapter(driverLegitimateExaminationAdapter);

        fireRecyclerView.setLayoutManager(fireLinearLayoutManager);
        fireLegitimateExaminationAdapter = new FireLegitimateExaminationAdapter(context, fragmentContext);
        fireRecyclerView.setAdapter(fireLegitimateExaminationAdapter);

        travelRecyclerView.setLayoutManager(travelLinearLayoutManager);
        travelLegitimateExaminationAdapter = new TravelLegitimateExaminationAdapter(context, fragmentContext);
        travelRecyclerView.setAdapter(travelLegitimateExaminationAdapter);

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        carItems = new Vector<>();
        driverItems = new Vector<>();
        fireItems = new Vector<>();
        travelItems = new Vector<>();

        RetrofitTool.getAPIWithAuthorizationToken(token).getCarLegitimateExaminationList()
                .enqueue(new Callback<Pagination<List<CarLegitimateExaminationResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<CarLegitimateExaminationResponse>>> call,
                                           Response<Pagination<List<CarLegitimateExaminationResponse>>> response) {
                        if(response.isSuccessful()){

                            for(CarLegitimateExaminationResponse res: response.body().getData()){
                                Log.e("res", res.toString());
                                carItems.add(res);
                            }

                            carLegitimateExaminationAdapter.addCarItems(carItems);
                            carLegitimateExaminationAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<CarLegitimateExaminationResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).getDriverLegitimateExaminationList()
                .enqueue(new Callback<Pagination<List<DriverLegitimateExaminationResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<DriverLegitimateExaminationResponse>>> call,
                                           Response<Pagination<List<DriverLegitimateExaminationResponse>>> response) {
                        if(response.isSuccessful()){
                            driverItems = new Vector<>();
                            for(DriverLegitimateExaminationResponse res: response.body().getData()){
                                driverItems.add(res);
                            }
                            driverLegitimateExaminationAdapter.addDriverItems(driverItems);
                            driverLegitimateExaminationAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<DriverLegitimateExaminationResponse>>> call, Throwable t) {
                    }
                });

        RetrofitTool.getAPIWithAuthorizationToken(token).getFireLegitimateExaminationList()
                .enqueue(new Callback<Pagination<List<FireLegitimateExaminationResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<FireLegitimateExaminationResponse>>> call,
                                           Response<Pagination<List<FireLegitimateExaminationResponse>>> response) {
                        if(response.isSuccessful()){
                            fireItems = new Vector<>();
                            for(FireLegitimateExaminationResponse res: response.body().getData()){
                                fireItems.add(res);
                            }
                            fireLegitimateExaminationAdapter.addFireItems(fireItems);
                            fireLegitimateExaminationAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<FireLegitimateExaminationResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).getTravelLegitimateExaminationList()
                .enqueue(new Callback<Pagination<List<TravelLegitimateExaminationResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<TravelLegitimateExaminationResponse>>> call,
                                           Response<Pagination<List<TravelLegitimateExaminationResponse>>> response) {
                        if(response.isSuccessful()){
                            travelItems = new Vector<>();
                            for(TravelLegitimateExaminationResponse res: response.body().getData()){
                                travelItems.add(res);
                            }
                            travelLegitimateExaminationAdapter.addTravelItems(travelItems);
                            travelLegitimateExaminationAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<TravelLegitimateExaminationResponse>>> call, Throwable t) {
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tvLegitimateExaminationToolbar);
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

    public static LegitimateExaminationFirstFragment newInstance() {
        return new LegitimateExaminationFirstFragment();
    }
}