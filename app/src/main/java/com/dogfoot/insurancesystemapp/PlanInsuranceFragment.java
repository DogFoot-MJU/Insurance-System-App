package com.dogfoot.insurancesystemapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogfoot.insurancesystemapp.databinding.ActivityMainBinding;
import com.dogfoot.insurancesystemapp.databinding.ActivityPlanInsuranceBinding;

import java.util.ArrayList;

public class PlanInsuranceFragment extends Fragment {

    private ActivityPlanInsuranceBinding mBinding;
    private InsuracncePlanningAdapter insuracncePlanningAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    private ArrayList<InsurancePlanning> insuarancePlanningList;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_plan_insurance);
//
//        // Set Binding
//        mBinding = ActivityPlanInsuranceBinding.inflate(getLayoutInflater());
//        View view = mBinding.getRoot();
//        setContentView(view);
//
//        recyclerView = mBinding.rvInsurancePlanning;
//        insuarancePlanningList = new ArrayList<>();
//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        // 서버에서 ArrayList로 값을 가져오면 insuarancePlanningList에 넣으면 됨
//        InsurancePlanning insurancePlanning = new InsurancePlanning("정우", "리사이클러뷰");
//        InsurancePlanning insurancePlanning2 = new InsurancePlanning("정우2", "리사이클러뷰2");
//        for(int i=0; i<10; i++) {
//            insuarancePlanningList.add(insurancePlanning);
//            insuarancePlanningList.add(insurancePlanning2);
//        }
//        insuracncePlanningAdapter = new InsuracncePlanningAdapter(insuarancePlanningList);
//        insuracncePlanningAdapter.notifyDataSetChanged(); // 새로고침 해준다. add나 modify후 새로고침을 해야함
//        recyclerView.setAdapter(insuracncePlanningAdapter);
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // fragment는 onCreateView로 생성하면 된다.

        mBinding = ActivityPlanInsuranceBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        recyclerView = mBinding.rvInsurancePlanning;
        insuarancePlanningList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        // 서버에서 ArrayList로 값을 가져오면 insuarancePlanningList에 넣으면 됨
        InsurancePlanning insurancePlanning = new InsurancePlanning("정우", "리사이클러뷰");
        InsurancePlanning insurancePlanning2 = new InsurancePlanning("정우2", "리사이클러뷰2");
        for(int i=0; i<10; i++) {
            insuarancePlanningList.add(insurancePlanning);
            insuarancePlanningList.add(insurancePlanning2);
        }
        insuracncePlanningAdapter = new InsuracncePlanningAdapter(insuarancePlanningList);
        insuracncePlanningAdapter.notifyDataSetChanged(); // 새로고침 해준다. add나 modify후 새로고침을 해야함
        recyclerView.setAdapter(insuracncePlanningAdapter);

        return view;
    }

}