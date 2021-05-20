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
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter.PlanningInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.Pagination;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanInsuranceFirstFragment extends DogFootViewModelFragment {

    private FragmentPlanInsuranceFirstBinding mBinding;
    private PlanningInsuranceAdapter planningInsuranceAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Context context;
    private FragmentActivity fragmentContext;

    Vector<CarPlanInsuranceResponse> items;

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

        recyclerView = mBinding.rvInsurancePlanning;
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        planningInsuranceAdapter = new PlanningInsuranceAdapter(context, fragmentContext);
        planningInsuranceAdapter.notifyDataSetChanged(); // 새로고침 해준다. add나 modify후 새로고침을 해야함
        recyclerView.setAdapter(planningInsuranceAdapter);

        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token).carPlanInsurance()
                .enqueue(new Callback<Pagination<List<CarPlanInsuranceResponse>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<CarPlanInsuranceResponse>>> call,
                                           Response<Pagination<List<CarPlanInsuranceResponse>>> response) {
                        if(response.isSuccessful()){
                            items = new Vector<>();
                            for(CarPlanInsuranceResponse res: response.body().getData()){
                                items.add(res);
                            }
                            planningInsuranceAdapter.addItem(items);
                            planningInsuranceAdapter.notifyDataSetChanged();
                        } else{
                            System.out.println(response);
                            System.out.println(response.errorBody());
                            System.out.println(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Pagination<List<CarPlanInsuranceResponse>>> call, Throwable t) {
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