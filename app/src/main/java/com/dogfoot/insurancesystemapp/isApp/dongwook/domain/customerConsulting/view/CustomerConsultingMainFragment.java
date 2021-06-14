package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.view.LoginActivity;

public class CustomerConsultingMainFragment extends DogFootViewModelFragment {


    private Button inputButton,  listButton,resultButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer_consulting_main;
    }



    @Override
    protected void associateView(View view) {
        this.inputButton=view.findViewById(R.id.customerConsultingMainFragment_inputButton);
        this.listButton=view.findViewById(R.id.customerConsultingMainFragment_listButton);



    }

    @Override
    protected void initializeView() {
        this.inputButton.setOnClickListener(v->this.navigateTo(R.id.action_customerConsultingMain_to_customerConsultingInput));
        this.listButton.setOnClickListener(v->this.navigateTo(R.id.action_customerConsultingMain_to_customerConsultingList));


    }

    @Override
    public void dogFootEntityUpdated() {

    }


}
