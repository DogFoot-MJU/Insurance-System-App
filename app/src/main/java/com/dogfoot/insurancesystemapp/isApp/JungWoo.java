package com.dogfoot.insurancesystemapp.isApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.ActivityDrawerBinding;
import com.dogfoot.insurancesystemapp.databinding.ActivityJungWooBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class JungWoo extends AppCompatActivity{

    private ActivityJungWooBinding mBinding;
    //private ActivityDrawerBinding drawerBinding;
    private PlanInsuranceFragment planInsuranceFragment;
    private Fragment1 fragment;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button btn_close;

    private TextView tv_insuranceDevInfo, tv_insuranceInitInfo, tv_insuranceDesignInfo, tv_insuranceSendInfo;
    private TextView tv_uwInfo, tv_uwLossLateInfo, tv_uwAcquisitionPolicyInfo, tv_uwAppropriateExaminationInfo;
    private TextView tv_userInfo, tv_userApplicationInfo, tv_userCounselingInfo;
    private TextView tv_salesInfo, tv_salesInsuranceConclusionInfo, tv_userSalesCallManagementInfo;
    private TextView tv_contractInfo, tv_contractManagementInfo, tv_managementOfContractManagementGuidelinesInfo, tv_managingExpirationContractsInfo;
    private TextView tv_compensationInfo, tv_incidentReceptionInfo, tv_damageSituationInfo, tv_damageInvestigationInfo, tv_compensationPaidInfo,
            tv_compensationManagementInfo, tv_compensationEvaluationManagementInfo;


    private void drawerInit() {
        tv_insuranceDevInfo = findViewById(R.id.tv_insuranceDevInfo);
        tv_insuranceInitInfo = findViewById(R.id.tv_insuranceInitInfo);
        tv_insuranceDesignInfo = findViewById(R.id.tv_insuranceDesignInfo);
        tv_insuranceSendInfo = findViewById(R.id.tv_insuranceSendInfo);

        tv_uwInfo = findViewById(R.id.tv_uwInfo);
        tv_uwLossLateInfo = findViewById(R.id.tv_uwLossLateInfo);
        tv_uwAcquisitionPolicyInfo = findViewById(R.id.tv_uwAcquisitionPolicyInfo);
        tv_uwAppropriateExaminationInfo = findViewById(R.id.tv_uwAppropriateExaminationInfo);

        tv_userInfo = findViewById(R.id.tv_userInfo);
        tv_userApplicationInfo = findViewById(R.id.tv_userApplicationInfo);
        tv_userCounselingInfo = findViewById(R.id.tv_userCounselingInfo);

        tv_salesInfo = findViewById(R.id.tv_salesInfo);
        tv_salesInsuranceConclusionInfo = findViewById(R.id.tv_salesInsuranceConclusionInfo);
        tv_userSalesCallManagementInfo = findViewById(R.id.tv_userSalesCallManagementInfo);

        tv_contractInfo = findViewById(R.id.tv_contractInfo);
        tv_contractManagementInfo = findViewById(R.id.tv_contractManagementInfo);
        tv_managementOfContractManagementGuidelinesInfo = findViewById(R.id.tv_managementOfContractManagementGuidelinesInfo);
        tv_managingExpirationContractsInfo = findViewById(R.id.tv_managingExpirationContractsInfo);

        tv_compensationInfo = findViewById(R.id.tv_compensationInfo);
        tv_incidentReceptionInfo = findViewById(R.id.tv_incidentReceptionInfo);
        tv_damageSituationInfo = findViewById(R.id.tv_damageSituationInfo);
        tv_damageInvestigationInfo = findViewById(R.id.tv_damageInvestigationInfo);
        tv_compensationPaidInfo = findViewById(R.id.tv_compensationPaidInfo);
        tv_compensationManagementInfo = findViewById(R.id.tv_compensationManagementInfo);
        tv_compensationEvaluationManagementInfo = findViewById(R.id.tv_compensationEvaluationManagementInfo);

        tv_insuranceDevInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_insuranceInitInfo.getVisibility()==View.VISIBLE){
                    tv_insuranceInitInfo.setVisibility(View.GONE);
                    tv_insuranceDesignInfo.setVisibility(View.GONE);
                    tv_insuranceSendInfo.setVisibility(View.GONE);
                }
                else{
                    tv_insuranceInitInfo.setVisibility(View.VISIBLE);
                    tv_insuranceDesignInfo.setVisibility(View.VISIBLE);
                    tv_insuranceSendInfo.setVisibility(View.VISIBLE);
                }
            }
        });
        tv_uwInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_uwLossLateInfo.getVisibility()==View.VISIBLE){
                    tv_uwLossLateInfo.setVisibility(View.GONE);
                    tv_uwAcquisitionPolicyInfo.setVisibility(View.GONE);
                    tv_uwAppropriateExaminationInfo.setVisibility(View.GONE);
                }
                else{
                    tv_uwLossLateInfo.setVisibility(View.VISIBLE);
                    tv_uwAcquisitionPolicyInfo.setVisibility(View.VISIBLE);
                    tv_uwAppropriateExaminationInfo.setVisibility(View.VISIBLE);
                }
            }
        });
        tv_userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_userApplicationInfo.getVisibility()==View.VISIBLE){
                    tv_userApplicationInfo.setVisibility(View.GONE);
                    tv_userCounselingInfo.setVisibility(View.GONE);
                }
                else{
                    tv_userApplicationInfo.setVisibility(View.VISIBLE);
                    tv_userCounselingInfo.setVisibility(View.VISIBLE);
                }
            }
        });
        tv_salesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_salesInsuranceConclusionInfo.getVisibility()==View.VISIBLE){
                    tv_salesInsuranceConclusionInfo.setVisibility(View.GONE);
                    tv_userSalesCallManagementInfo.setVisibility(View.GONE);
                }
                else{
                    tv_salesInsuranceConclusionInfo.setVisibility(View.VISIBLE);
                    tv_userSalesCallManagementInfo.setVisibility(View.VISIBLE);
                }
            }
        });
        tv_contractInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_contractManagementInfo.getVisibility()==View.VISIBLE){
                    tv_contractManagementInfo.setVisibility(View.GONE);
                    tv_managementOfContractManagementGuidelinesInfo.setVisibility(View.GONE);
                    tv_managingExpirationContractsInfo.setVisibility(View.GONE);
                }
                else{
                    tv_contractManagementInfo.setVisibility(View.VISIBLE);
                    tv_managementOfContractManagementGuidelinesInfo.setVisibility(View.VISIBLE);
                    tv_managingExpirationContractsInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        tv_compensationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_incidentReceptionInfo.getVisibility()==View.VISIBLE){
                    tv_incidentReceptionInfo.setVisibility(View.GONE);
                    tv_damageSituationInfo.setVisibility(View.GONE);
                    tv_damageInvestigationInfo.setVisibility(View.GONE);
                    tv_compensationPaidInfo.setVisibility(View.GONE);
                    tv_compensationManagementInfo.setVisibility(View.GONE);
                    tv_compensationEvaluationManagementInfo.setVisibility(View.GONE);
                }
                else{
                    tv_incidentReceptionInfo.setVisibility(View.VISIBLE);
                    tv_damageSituationInfo.setVisibility(View.VISIBLE);
                    tv_damageInvestigationInfo.setVisibility(View.VISIBLE);
                    tv_compensationPaidInfo.setVisibility(View.VISIBLE);
                    tv_compensationManagementInfo.setVisibility(View.VISIBLE);
                    tv_compensationEvaluationManagementInfo.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set Binding
        mBinding = ActivityJungWooBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);


        fragment = new Fragment1();
        drawerLayout = mBinding.drawerLayout;
        drawerView = findViewById(R.id.drawer);
        btn_close = findViewById(R.id.btn_close);

        drawerInit();


        mBinding.btnPlanInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                PlanInsuranceFragment planInsuranceFragment = new PlanInsuranceFragment();
                transaction.replace(R.id.fl_main, planInsuranceFragment);
                transaction.commit(); //새로고침
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });
        DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) { }
            @Override
            public void onDrawerOpened(@NonNull View drawerView) { }
            @Override
            public void onDrawerClosed(@NonNull View drawerView) { }
            @Override
            public void onDrawerStateChanged(int newState) { }
        };

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        mBinding.bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_menu:
                      openDrawerLayout();
                        break;
                    case R.id.action_search:
                        //setFrag(1);
                        break;
                    case R.id.action_home:
                        //setFrag(2);
                        break;
                    case R.id.action_myPage:
                        //setFrag(3);
                        break;
                    case R.id.action_setting:
                        //setFrag(4);
                        break;
                }
                return true;
            }
        });
        setFrag(2); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택
    }


    private void openDrawerLayout() {
        drawerLayout.openDrawer(drawerView);
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.fl_main, planInsuranceFragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.fl_main, planInsuranceFragment);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.fl_main, fragment);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.fl_main, planInsuranceFragment);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.fl_main, planInsuranceFragment);
                ft.commit();
                break;
        }
    }

}