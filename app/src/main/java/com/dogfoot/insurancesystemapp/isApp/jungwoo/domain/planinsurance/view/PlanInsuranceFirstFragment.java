package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view;

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
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.HomeFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.InsuracncePlanningAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.InsurancePlanning;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanInsuranceFirstFragment extends DogFootViewModelFragment {

    private FragmentPlanInsuranceFirstBinding mBinding;
    private InsuracncePlanningAdapter insuracncePlanningAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Context context;
    private FragmentActivity fragmentContext;

    private Vector<InsurancePlanning> insuarancePlanningList;
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
        insuarancePlanningList = new Vector<>();
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

//        String token = this.dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION);
//        RetrofitTool.getAPIWithAuthorizationToken(token).carPlanInsurance().enqueue(new Callback<List<CarPlanInsuranceResponse>>() {
//            @Override
//            public void onResponse(Call<List<CarPlanInsuranceResponse>> call, Response<List<CarPlanInsuranceResponse>> response) {
//                if(response.isSuccessful()){
//                    items = new Vector<CarPlanInsuranceResponse>(response.body());
//                } else{
//                    Log.e("서버오류", "오류");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<CarPlanInsuranceResponse>> call, Throwable t) {
//
//            }
//        });

        // 서버에서 ArrayList로 값을 가져오면 insuarancePlanningList에 넣으면 됨
//        InsurancePlanning insurancePlanning = new InsurancePlanning(1, "자동차보험1", "1000", "state", "Fire");
//        InsurancePlanning insurancePlanning2 = new InsurancePlanning(2, "운전자보험2", "3000", "state", "car");
//        for(int i=0; i<10; i++) {
//            insuarancePlanningList.add(insurancePlanning);
//            insuarancePlanningList.add(insurancePlanning2);
//        }
        //insuarancePlanningList = items;

        insuracncePlanningAdapter = new InsuracncePlanningAdapter(items, context, fragmentContext);
        insuracncePlanningAdapter.notifyDataSetChanged(); // 새로고침 해준다. add나 modify후 새로고침을 해야함
        recyclerView.setAdapter(insuracncePlanningAdapter);

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