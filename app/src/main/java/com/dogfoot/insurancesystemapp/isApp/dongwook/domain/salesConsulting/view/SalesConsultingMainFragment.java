package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view;

import android.view.View;
import android.widget.Button;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;

public class SalesConsultingMainFragment extends DogFootViewModelFragment {


    private Button listButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sales_consulting_main;
    }



    @Override
    protected void associateView(View view) {
        this.listButton=view.findViewById(R.id.salesConsultingMainFragment_listButton);


    }

    @Override
    protected void initializeView() {
        this.listButton.setOnClickListener(v->this.navigateTo(R.id.action_salesConsultingMain_to_salesConsultingList));

    }

    @Override
    public void dogFootEntityUpdated() {

    }


}
