package com.dogfoot.insurancesystemapp.isApp.jungwoo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.ActivityJungWooBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view.CustomerCompensationMainActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingMainActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.logout.view.LogOutActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view.SalesCompensationMainActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view.SalesConsultingMainActivity;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.advertisement.AdvertisementViewPagerAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.advertisement.ViewPagerTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.approveinsurance.view.ApproveInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.authorizeinsurance.view.AuthorizeInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view.InsuranceApplicationFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.view.LegitimateExaminationFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view.PlanInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.user.model.UserResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private TextView tv_userName, tv_userRole;
    private TextView tv_insuranceDevInfo, tv_insuranceInitInfo, tv_insuranceDesignInfo, tv_insuranceSendInfo, tv_insuranceApproveInfo;
    private TextView tv_uwInfo, tv_uwAcquisitionPolicyInfo, tv_uwAppropriateExaminationInfo;
    private TextView tv_userInfo, tv_userApplicationInfo, tv_userCounselingInfo, tv_userReceiptCounselingInfo;
    private TextView tv_salesInfo, tv_userSalesCallManagementInfo;
    private TextView tv_compensationInfo, tv_incidentReceptionInfo;

    private void setRole(Response<UserResponse> response) {
        if(response.body().getRole_name().equals(Constant.UserRoleType.ROLE_INSURANCE_PLANNER.getName())){
            tv_uwInfo.setVisibility(View.GONE);
            tv_userInfo.setVisibility(View.GONE);
            tv_salesInfo.setVisibility(View.GONE);
            tv_compensationInfo.setVisibility(View.GONE);
        }  else if(response.body().getRole_name().equals(Constant.UserRoleType.ROLE_UW.getName())){
            tv_insuranceDevInfo.setVisibility(View.GONE);
            tv_userInfo.setVisibility(View.GONE);
            tv_salesInfo.setVisibility(View.GONE);
            tv_compensationInfo.setVisibility(View.GONE);
        } else if(response.body().getRole_name().equals(Constant.UserRoleType.ROLE_USER.getName())){
            tv_insuranceDevInfo.setVisibility(View.GONE);
            tv_uwInfo.setVisibility(View.GONE);
            tv_salesInfo.setVisibility(View.GONE);
            tv_compensationInfo.setVisibility(View.GONE);
        }else if(response.body().getRole_name().equals(Constant.UserRoleType.ROLE_INSURANCE_SELLER.getName())){
            tv_insuranceDevInfo.setVisibility(View.GONE);
            tv_userInfo.setVisibility(View.GONE);
            tv_uwInfo.setVisibility(View.GONE);
            tv_compensationInfo.setVisibility(View.GONE);
        } else if(response.body().getRole_name().equals(Constant.UserRoleType.ROLE_INSURANCE_COMPENSATION_HANDLER.getName())){
            tv_insuranceDevInfo.setVisibility(View.GONE);
            tv_userInfo.setVisibility(View.GONE);
            tv_salesInfo.setVisibility(View.GONE);
            tv_uwInfo.setVisibility(View.GONE);
        }
        tv_userName.setText(response.body().getName());
        tv_userRole.setText(response.body().getRole_name());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set Binding
        mBinding = ActivityJungWooBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);


        //initToolbar();
        drawerInit();
        navigationBottomInit();

        // set user
        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token).getRole().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                setRole(response);
            }


            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

        mainFragment = new HomeFragment();

    }

    private void navigationBottomInit() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_main, HomeFragment.newInstance()).commit();

    }


    private void openDrawerLayout() {
        drawerLayout.openDrawer(drawerView);
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

        tv_userName = findViewById(R.id.tv_name);
        tv_userRole = findViewById(R.id.tv_role);

        tv_insuranceDevInfo = findViewById(R.id.tv_insuranceDevInfo);
        tv_insuranceInitInfo = findViewById(R.id.tv_insuranceInitInfo);
        tv_insuranceDesignInfo = findViewById(R.id.tv_insuranceDesignInfo);
        tv_insuranceSendInfo = findViewById(R.id.tv_insuranceSendInfo);
        tv_insuranceApproveInfo = findViewById(R.id.tv_insuranceApproveInfo);

        tv_uwInfo = findViewById(R.id.tv_uwInfo);
        tv_uwAcquisitionPolicyInfo = findViewById(R.id.tv_uwAcquisitionPolicyInfo);
        tv_uwAppropriateExaminationInfo = findViewById(R.id.tv_uwAppropriateExaminationInfo);

        tv_userInfo = findViewById(R.id.tv_userInfo);
        tv_userApplicationInfo = findViewById(R.id.tv_userApplicationInfo);
        tv_userCounselingInfo = findViewById(R.id.tv_userCounselingInfo);
        tv_userReceiptCounselingInfo = findViewById(R.id.tv_userReceiptCounselingInfo);

        tv_salesInfo = findViewById(R.id.tv_salesInfo);
        tv_userSalesCallManagementInfo = findViewById(R.id.tv_userSalesCallManagementInfo);

        tv_compensationInfo = findViewById(R.id.tv_compensationInfo);
        tv_incidentReceptionInfo = findViewById(R.id.tv_incidentReceptionInfo);

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
                if(tv_uwAcquisitionPolicyInfo.getVisibility()==View.VISIBLE){
                    tv_uwAcquisitionPolicyInfo.setVisibility(View.GONE);
                    tv_uwAppropriateExaminationInfo.setVisibility(View.GONE);
                }
                else{
                    tv_uwAcquisitionPolicyInfo.setVisibility(View.VISIBLE);
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
                    tv_userReceiptCounselingInfo.setVisibility(View.GONE);
                }
                else{
                    tv_userApplicationInfo.setVisibility(View.VISIBLE);
                    tv_userCounselingInfo.setVisibility(View.VISIBLE);
                    tv_userReceiptCounselingInfo.setVisibility(View.VISIBLE);
                }
            }
        });
        tv_salesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_userSalesCallManagementInfo.getVisibility()==View.VISIBLE){
                    tv_userSalesCallManagementInfo.setVisibility(View.GONE);
                }
                else{
                    tv_userSalesCallManagementInfo.setVisibility(View.VISIBLE);
                    tv_userSalesCallManagementInfo.setVisibility(View.VISIBLE);
                }
            }
        });





        tv_compensationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_incidentReceptionInfo.getVisibility()==View.VISIBLE){
                    tv_incidentReceptionInfo.setVisibility(View.GONE);
                }
                else{
                    tv_incidentReceptionInfo.setVisibility(View.VISIBLE);
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

        tv_userReceiptCounselingInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerCompensationMainActivity.class);
                startActivity(intent);
            }
        });



        tv_userCounselingInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerConsultingMainActivity.class);
                startActivity(intent);
            }
        });

        tv_userSalesCallManagementInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SalesConsultingMainActivity.class);
                startActivity(intent);
            }
        });

        tv_incidentReceptionInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SalesCompensationMainActivity.class);
                startActivity(intent);
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogOutActivity.class);
                startActivity(intent);
                finish();
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

        tv_uwAppropriateExaminationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_main, LegitimateExaminationFirstFragment.newInstance());
                transaction.commit();
            }
        });

        tv_userApplicationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_main, InsuranceApplicationFragment.newInstance());
                transaction.commit();
            }
        });

    }

    @Override
    protected void associateView() {

    }

    @Override
    protected void initializeView() {


    }

    @Override
    public void dogFootEntityUpdated() {

    }

}