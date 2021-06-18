package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view;

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
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentPlanInsuranceFirstBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.CarPlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.DriverPlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.FirePlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.TravelPlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceResponse;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanInsuranceFirstFragment extends DogFootViewModelFragment {

    private FragmentPlanInsuranceFirstBinding mBinding;
    private CarPlanningInsuranceAdapter carPlanningInsuranceAdapter;
    private DriverPlanningInsuranceAdapter driverPlanningInsuranceAdapter;
    private FirePlanningInsuranceAdapter firePlanningInsuranceAdapter;
    private TravelPlanningInsuranceAdapter travelPlanningInsuranceAdapter;
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

    Vector<CarPlanInsuranceResponse> carItems;
    Vector<DriverPlanInsuranceResponse> driverItems;
    Vector<FirePlanInsuranceResponse> fireItems;
    Vector<TravelPlanInsuranceResponse> travelItems;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // fragment는 onCreateView로 생성하면 된다.

        mBinding = FragmentPlanInsuranceFirstBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        setHasOptionsMenu(true);
        initToolbar();
        init();

        carRecyclerView = mBinding.rvCarInsurancePlanning;
        driverRecyclerView = mBinding.rvDriverInsurancePlanning;
        fireRecyclerView = mBinding.rvFireInsurancePlanning;
        travelRecyclerView = mBinding.rvTravelInsurancePlanning;
        carLinearLayoutManager = new LinearLayoutManager(context);
        driverLinearLayoutManager = new LinearLayoutManager(context);
        fireLinearLayoutManager = new LinearLayoutManager(context);
        travelLinearLayoutManager = new LinearLayoutManager(context);

        carRecyclerView.setLayoutManager(carLinearLayoutManager);
        carPlanningInsuranceAdapter = new CarPlanningInsuranceAdapter(context, fragmentContext, false);
        carRecyclerView.setAdapter(carPlanningInsuranceAdapter);

        driverRecyclerView.setLayoutManager(driverLinearLayoutManager);
        driverPlanningInsuranceAdapter = new DriverPlanningInsuranceAdapter(context, fragmentContext, false);
        driverRecyclerView.setAdapter(driverPlanningInsuranceAdapter);

        fireRecyclerView.setLayoutManager(fireLinearLayoutManager);
        firePlanningInsuranceAdapter = new FirePlanningInsuranceAdapter(context, fragmentContext, false);
        fireRecyclerView.setAdapter(firePlanningInsuranceAdapter);

        travelRecyclerView.setLayoutManager(travelLinearLayoutManager);
        travelPlanningInsuranceAdapter = new TravelPlanningInsuranceAdapter(context, fragmentContext, false);
        travelRecyclerView.setAdapter(travelPlanningInsuranceAdapter);

        Constant constant = Constant.getInstance();
       String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        carItems = new Vector<>();
        driverItems = new Vector<>();
        fireItems = new Vector<>();
        travelItems = new Vector<>();
        RetrofitTool.getAPIWithAuthorizationToken(token).carPlanInsurance()
                .enqueue(new Callback<Pagination<List<CarPlanInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<CarPlanInsuranceResponse>>> call,
                                           Response<Pagination<List<CarPlanInsuranceResponse>>> response) {
                        if(response.isSuccessful()){

                            for(CarPlanInsuranceResponse res: response.body().getData()){
                                carItems.add(res);
                            }
                            carPlanningInsuranceAdapter.addCarItems(carItems);
                            carPlanningInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<CarPlanInsuranceResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).driverPlanInsurance()
                .enqueue(new Callback<Pagination<List<DriverPlanInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<DriverPlanInsuranceResponse>>> call,
                                           Response<Pagination<List<DriverPlanInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            driverItems = new Vector<>();
                            for(DriverPlanInsuranceResponse res: response.body().getData()){
                                driverItems.add(res);
                            }
                            driverPlanningInsuranceAdapter.addDriverItems(driverItems);
                            driverPlanningInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<DriverPlanInsuranceResponse>>> call, Throwable t) {
                    }
                });

        RetrofitTool.getAPIWithAuthorizationToken(token).firePlanInsurance()
                .enqueue(new Callback<Pagination<List<FirePlanInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<FirePlanInsuranceResponse>>> call,
                                           Response<Pagination<List<FirePlanInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            fireItems = new Vector<>();
                            for(FirePlanInsuranceResponse res: response.body().getData()){
                                fireItems.add(res);
                            }
                            firePlanningInsuranceAdapter.addFireItems(fireItems);
                            firePlanningInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<FirePlanInsuranceResponse>>> call, Throwable t) {
                    }
                });
        RetrofitTool.getAPIWithAuthorizationToken(token).travelPlanInsurance()
                .enqueue(new Callback<Pagination<List<TravelPlanInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<TravelPlanInsuranceResponse>>> call,
                                           Response<Pagination<List<TravelPlanInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            travelItems = new Vector<>();
                            for(TravelPlanInsuranceResponse res: response.body().getData()){
                                travelItems.add(res);
                            }
                            travelPlanningInsuranceAdapter.addTravelItems(travelItems);
                            travelPlanningInsuranceAdapter.notifyDataSetChanged();
                        } else{
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<TravelPlanInsuranceResponse>>> call, Throwable t) {
                    }
                });

        return view;
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

    private void init() {
        mBinding.buttonInsurancePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(PlanInsuranceSecondFragment.newInstance());
            }
        });
    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbPlanInsuranceToolbar);
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

    public static PlanInsuranceFirstFragment newInstance() {
        return new PlanInsuranceFirstFragment();
    }

}