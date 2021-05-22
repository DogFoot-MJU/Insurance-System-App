package com.dogfoot.insurancesystemapp.isApp.jungwoo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.ActivityJungWooBinding;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.approveinsurance.view.ApproveInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.authorizeinsurance.view.AuthorizeInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view.PlanInsuranceFirstFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JungWoo extends DogFootViewModelActivity {

    private ActivityJungWooBinding mBinding;
    private PlanInsuranceFirstFragment planInsuranceFirstFragment;
    private HomeFragment mainFragment;
    private FragmentManager fm;
    private FragmentTransaction ft;

    // drawer Attributes
    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button btn_close;
    private TextView tv_insuranceDevInfo, tv_insuranceInitInfo, tv_insuranceDesignInfo, tv_insuranceSendInfo, tv_insuranceApproveInfo;
    private TextView tv_uwInfo, tv_uwLossLateInfo, tv_uwAcquisitionPolicyInfo, tv_uwAppropriateExaminationInfo;
    private TextView tv_userInfo, tv_userApplicationInfo, tv_userCounselingInfo;
    private TextView tv_salesInfo, tv_salesInsuranceConclusionInfo, tv_userSalesCallManagementInfo;
    private TextView tv_contractInfo, tv_contractManagementInfo, tv_managementOfContractManagementGuidelinesInfo, tv_managingExpirationContractsInfo;
    private TextView tv_compensationInfo, tv_incidentReceptionInfo, tv_damageSituationInfo, tv_damageInvestigationInfo, tv_compensationPaidInfo,
            tv_compensationManagementInfo, tv_compensationEvaluationManagementInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set Binding
        mBinding = ActivityJungWooBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mainFragment = new HomeFragment();

        //initToolbar();
        mainInit();
        drawerInit();
        navigationBottomInit();

    }

    private void mainInit() {

    }

    private void navigationBottomInit() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_main, HomeFragment.newInstance()).commit();
        mBinding.bottomNavi.setSelectedItemId(R.id.action_home);

        mBinding.bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_menu:
                        openDrawerLayout();
                        break;
                    case R.id.action_search:
                        setFrag(1);
                        break;
                    case R.id.action_home:
                        setFrag(2);
                        break;
                    case R.id.action_myPage:
                        setFrag(3);
                        break;
                    case R.id.action_setting:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });

    }


    private void openDrawerLayout() {
        drawerLayout.openDrawer(drawerView);
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                break;
            case 1:
                replaceFragment(SearchFragment.newInstance());
                break;
            case 2:
                replaceFragment(HomeFragment.newInstance());
                break;
            case 3:
                replaceFragment(MyPageFragment.newInstance());
                break;
            case 4:
//                ft.replace(R.id.fl_main, planInsuranceFragment);
//                ft.commit();
                break;
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment).commit();
    }

    private void drawerInit() {
        drawerLayout = mBinding.drawerLayout;
        drawerView = findViewById(R.id.drawer);
        btn_close = findViewById(R.id.btn_close);

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

        tv_insuranceDevInfo = findViewById(R.id.tv_insuranceDevInfo);
        tv_insuranceInitInfo = findViewById(R.id.tv_insuranceInitInfo);
        tv_insuranceDesignInfo = findViewById(R.id.tv_insuranceDesignInfo);
        tv_insuranceSendInfo = findViewById(R.id.tv_insuranceSendInfo);
        tv_insuranceApproveInfo = findViewById(R.id.tv_insuranceApproveInfo);

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
                    tv_insuranceApproveInfo.setVisibility(View.GONE);
                }
                else{
                    tv_insuranceInitInfo.setVisibility(View.VISIBLE);
                    tv_insuranceDesignInfo.setVisibility(View.VISIBLE);
                    tv_insuranceSendInfo.setVisibility(View.VISIBLE);
                    tv_insuranceApproveInfo.setVisibility(View.VISIBLE);
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

        tv_insuranceInitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_main, planInsuranceFirstFragment.newInstance());
                transaction.commit();
            }
        });

        tv_insuranceDesignInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_main, DesignInsuranceFirstFragment.newInstance());
                transaction.commit();
            }
        });

        tv_insuranceSendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_main, AuthorizeInsuranceFirstFragment.newInstance());
                transaction.commit();
            }
        });
        tv_insuranceApproveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_main, ApproveInsuranceFirstFragment.newInstance());
                transaction.commit();
            }
        });
        tv_uwAcquisitionPolicyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_main, RegistrationCapacityPolicyFirstFragment.newInstance());
                transaction.commit();
            }
        });

    }

    @Override
    protected void associateView() {

    }

    @Override
    protected void initializeView() {
        Log.d("디버그",dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION));

    }

    @Override
    public void dogFootEntityUpdated() {

    }

}