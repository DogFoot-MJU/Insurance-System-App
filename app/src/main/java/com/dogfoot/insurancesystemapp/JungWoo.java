package com.dogfoot.insurancesystemapp;

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
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(), "열림", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
            }
        });
        DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
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