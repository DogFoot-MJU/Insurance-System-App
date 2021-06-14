package com.dogfoot.insurancesystemapp.isApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntityTool;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.DongWookActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.view.LoginActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view.SalesConsultingMainActivity;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.JungWoo;


public class MainActivity extends DogFootViewModelActivity {
    private long backKeyPressedTime = 0;
    private boolean valueInput=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override protected void associateView() { }
    @Override
    protected void initializeView() {

        dataset.remove(DogFootEntity.EDogFootData.AUTHORIZATION);
        dataset.remove(DogFootEntity.EDogFootData.EMAIL);
        dataset.remove(DogFootEntity.EDogFootData.PASSWORD);
        this.valueInput=true;
        this.save();
        if(valueInput) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
        }
    }








    @Override protected void onDestroy() { super.onDestroy();
        }

    public void save(){
        DogFootEntityTool.save(this.viewModelTool, this.entity);
    }

    @Override
    public void dogFootEntityUpdated() {

    }
    @Override
    public void onBackPressed() {
        // 기존의 뒤로가기 버튼의 기능 제거
        // super.onBackPressed();

        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2초 이내에 뒤로가기 버튼을 한번 더 클릭시 finish()(앱 종료)
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
        }
    }

}