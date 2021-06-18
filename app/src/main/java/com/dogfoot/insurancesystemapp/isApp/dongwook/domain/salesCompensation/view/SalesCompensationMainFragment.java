package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view;

import android.view.View;
import android.widget.Button;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;

public class SalesCompensationMainFragment extends DogFootViewModelFragment {


    private Button  listButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sales_compensation_main;
    }



    @Override
    protected void associateView(View view) {
        this.listButton=view.findViewById(R.id.salesCompensationMainFragment_listButton);
    }

    @Override
    protected void initializeView() {
        this.listButton.setOnClickListener(v->this.navigateTo(R.id.action_salesCompensationMain_to_salesContractList));

    }

    @Override
    public void dogFootEntityUpdated() {

    }


}
