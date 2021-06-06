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
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    private TextView tv_uwInfo, tv_uwAcquisitionPolicyInfo, tv_uwAppropriateExaminationInfo;
    private TextView tv_userInfo, tv_userApplicationInfo, tv_userCounselingInfo, tv_userReceiptCounselingInfo;
    private TextView tv_salesInfo, tv_userSalesCallManagementInfo;
    private TextView tv_compensationInfo, tv_incidentReceptionInfo;
    private CardView cardView_car, cardView_driver, cardView_fire, cardView_travel;
    private ViewPager2 viewPager2;
    @AllArgsConstructor
    @Getter
    public enum EAdImage{
        first(R.drawable.insurance3),second(R.drawable.insurance4),third(R.drawable.fire_insurance),fourth(R.drawable.travel_insurance);
        private int ImageId;
    }

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

        this.viewPager2 = findViewById(R.id.homeFragment_viewPager);
        this.viewPager2.setAdapter(new AdvertisementViewPagerAdapter(EAdImage.values(), getApplicationContext()));
        ViewPagerTool.setAutoSlide(this.viewPager2, 3000);
        ViewPagerTool.setEffect(this.viewPager2);

        cardView_car = findViewById(R.id.cardView_car);
        cardView_driver = findViewById(R.id.cardView_driver);
        cardView_fire = findViewById(R.id.cardView_fire);
        cardView_travel = findViewById(R.id.cardView_travel);

        cardView_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(JungWoo.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        " <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>자동차 가격: 책정된 가격의 + 1천만, + 3천만, +5천만원, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>주행 거리: 책정된 거리의 + 10000km, + 50000km, +100000km, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>출고 일자: 책정된 일자의 + 365일, + 730일, +1095일, 나머지의 단계로 나뉩니다. </p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p>\n" +
                                "            <p>ex) 기본금 * (1.0 + 0.4 + 0.2 + 0.3)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("자동차 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

        cardView_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(JungWoo.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        "  <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>운전 면허: 운전 면허의 종류를 확인합니다. 1종 보통, 2종 보통의 단계로 나뉩니다.</p>\n" +
                                "            <p>운전 면허 취득 일자: 책정된 일자의 + 365일, + 730일, +1095일, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p>\n" +
                                "            <p>ex) 기본금 * (1.0 + 0.2 + 0.1)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("운전자 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

        cardView_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(JungWoo.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        "   <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>빌딩 가격: 책정된 가격의 + 3억, + 6억, +9억, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>건축 일자: 책정된 일자의 + 365일, + 1095일, +2190일, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>층수: 책정된 층수의 + 5층, + 10층, +15층, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>부지 면적: 책정된 면적의 + 30m2, + 60m2, + 90m2, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p> \n" +
                                "            <p>ex) 기본금 * (1.0 + 0.2 + 0.1 +0.4 +0.3)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("화재 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

        cardView_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(JungWoo.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        " <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>안전 등급: 안전 등급의 기준을 확인합니다. GREEN, BLUE, RED의 단계로 나뉩니다.</p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p>\n" +
                                "            <p>ex) 기본금 * (1.0 + 0.2)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("여행 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

    }

    private void mainInit() {

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





//        tv_contractInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(tv_contractManagementInfo.getVisibility()==View.VISIBLE){
//                    tv_contractManagementInfo.setVisibility(View.GONE);
//                    tv_managementOfContractManagementGuidelinesInfo.setVisibility(View.GONE);
//                    tv_managingExpirationContractsInfo.setVisibility(View.GONE);
//                }
//                else{
//                    tv_contractManagementInfo.setVisibility(View.VISIBLE);
//                    tv_managementOfContractManagementGuidelinesInfo.setVisibility(View.VISIBLE);
//                    tv_managingExpirationContractsInfo.setVisibility(View.VISIBLE);
//                }
//            }
//        });

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