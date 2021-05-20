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
import com.dogfoot.insurancesystemapp.databinding.FragmentPlanInsuranceFirstBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.CarDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.DriverDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.FireDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.TravelDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.CarPlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.DriverPlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.FirePlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.TravelPlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view.PlanInsuranceFirstFragment;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignInsuranceFirstFragment extends DogFootViewModelFragment {

    private FragmentDesignInsuranceFirstBinding mBinding;
    private CarDesignInsuranceAdapter carDesignInsuranceAdapter;
    private DriverDesignInsuranceAdapter driverDesignInsuranceAdapter;
    private FireDesignInsuranceAdapter fireDesignInsuranceAdapter;
    private TravelDesignInsuranceAdapter travelDesignInsuranceAdapter;
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

    Vector<CarDesignInsuranceResponse> carItems;
    Vector<DriverDesignInsuranceResponse> driverItems;
    Vector<FireDesignInsuranceResponse> fireItems;
    Vector<TravelDesignInsuranceResponse> travelItems;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentDesignInsuranceFirstBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        init();

        carRecyclerView = mBinding.rvCarInsuranceDesign;
        driverRecyclerView = mBinding.rvDriverInsuranceDesign;
        fireRecyclerView = mBinding.rvFireInsuranceDesign;
        travelRecyclerView = mBinding.rvTravelInsuranceDesign;
        carLinearLayoutManager = new LinearLayoutManager(context);
        driverLinearLayoutManager = new LinearLayoutManager(context);
        fireLinearLayoutManager = new LinearLayoutManager(context);
        travelLinearLayoutManager = new LinearLayoutManager(context);

        carRecyclerView.setLayoutManager(carLinearLayoutManager);
        carDesignInsuranceAdapter = new CarDesignInsuranceAdapter(context, fragmentContext);
        carRecyclerView.setAdapter(carDesignInsuranceAdapter);

        driverRecyclerView.setLayoutManager(driverLinearLayoutManager);
        driverDesignInsuranceAdapter = new DriverDesignInsuranceAdapter(context, fragmentContext);
        driverRecyclerView.setAdapter(driverDesignInsuranceAdapter);

        fireRecyclerView.setLayoutManager(fireLinearLayoutManager);
        fireDesignInsuranceAdapter = new FireDesignInsuranceAdapter(context, fragmentContext);
        fireRecyclerView.setAdapter(fireDesignInsuranceAdapter);

        travelRecyclerView.setLayoutManager(travelLinearLayoutManager);
        travelDesignInsuranceAdapter = new TravelDesignInsuranceAdapter(context, fragmentContext);
        travelRecyclerView.setAdapter(travelDesignInsuranceAdapter);

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        carItems = new Vector<>();
        driverItems = new Vector<>();
        fireItems = new Vector<>();
        travelItems = new Vector<>();

        RetrofitTool.getAPIWithAuthorizationToken(token).carDesignInsurance()
                .enqueue(new Callback<Pagination<List<CarDesignInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<CarDesignInsuranceResponse>>> call,
                                           Response<Pagination<List<CarDesignInsuranceResponse>>> response) {
                        if(response.isSuccessful()){

                            for(CarDesignInsuranceResponse res: response.body().getData()){
                                carItems.add(res);
                            }
                            carDesignInsuranceAdapter.addCarItems(carItems);
                            carDesignInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<CarDesignInsuranceResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).driverDesignInsurance()
                .enqueue(new Callback<Pagination<List<DriverDesignInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<DriverDesignInsuranceResponse>>> call,
                                           Response<Pagination<List<DriverDesignInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            driverItems = new Vector<>();
                            for(DriverDesignInsuranceResponse res: response.body().getData()){
                                driverItems.add(res);
                            }
                            driverDesignInsuranceAdapter.addDriverItems(driverItems);
                            driverDesignInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<DriverDesignInsuranceResponse>>> call, Throwable t) {
                    }
                });

        RetrofitTool.getAPIWithAuthorizationToken(token).fireDesignInsurance()
                .enqueue(new Callback<Pagination<List<FireDesignInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<FireDesignInsuranceResponse>>> call,
                                           Response<Pagination<List<FireDesignInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            fireItems = new Vector<>();
                            for(FireDesignInsuranceResponse res: response.body().getData()){
                                fireItems.add(res);
                            }
                            fireDesignInsuranceAdapter.addFireItems(fireItems);
                            fireDesignInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<FireDesignInsuranceResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).travelDesignInsurance()
                .enqueue(new Callback<Pagination<List<TravelDesignInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<TravelDesignInsuranceResponse>>> call,
                                           Response<Pagination<List<TravelDesignInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            travelItems = new Vector<>();
                            for(TravelDesignInsuranceResponse res: response.body().getData()){
                                travelItems.add(res);
                            }
                            travelDesignInsuranceAdapter.addTravelItems(travelItems);
                            travelDesignInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<TravelDesignInsuranceResponse>>> call, Throwable t) {
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
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbDesignInsuranceToolbar);
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

    public static DesignInsuranceFirstFragment newInstance() {
        return new DesignInsuranceFirstFragment();
    }
}