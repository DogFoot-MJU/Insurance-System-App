package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.logout.view;

import android.os.Bundle;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.MainActivity;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;

public class LogOutActivity extends DogFootViewModelActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_logout);
    }
    @Override
    protected void associateView() {

    }

    @Override
    protected void initializeView() {

    }
    public void showOkDialog(){ this.showDialog(Constant.LOG_OUT_ACTIVITY_SUCCESSFUL_DIALOG_TITLE, Constant.LOG_OUT_ACTIVITY_SUCCESSFUL_DIALOG_MESSAGE); }
    public void showNoDialog(){ this.showDialog(Constant.LOG_OUT_ACTIVITY_FAILURE_DIALOG_TITLE, Constant.LOG_OUT_ACTIVITY_FAILURE_DIALOG_MESSAGE); }
    public void showDialog(String title, String message){ DogFootDialog.simplerAlertDialog(this, title, message, (dialog, which) -> this.startActivity(MainActivity.class)); }

    @Override
    public void dogFootEntityUpdated() {
        if(this.dataset.containsKey(DogFootEntity.EDogFootData.AUTHORIZATION)){
            this.dataset.remove(DogFootEntity.EDogFootData.AUTHORIZATION);
            this.dataset.remove(DogFootEntity.EDogFootData.EMAIL);
            this.dataset.remove(DogFootEntity.EDogFootData.PASSWORD);
            this.showOkDialog();
        }else{
            this.showNoDialog();
        }
    }
}
