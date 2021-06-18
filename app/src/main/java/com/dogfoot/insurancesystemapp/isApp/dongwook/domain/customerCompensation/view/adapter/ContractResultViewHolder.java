package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CompensationResultListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CustomerContractListResponse;

import java.text.SimpleDateFormat;


public class ContractResultViewHolder extends RecyclerView.ViewHolder {



    // Associate
        // View

        private TextView title,isApprove,expirationDate,expirationDateText;
        // ETC
        private Activity activity;


    // Constructor
    public ContractResultViewHolder(View itemView, View.OnClickListener onClickListener, Activity activity) {
        super(itemView);

        // Associate
        this.activity=activity;

        // Associate View
        this.expirationDateText = itemView.findViewById(R.id.item_customer_contract_time);
        this.title = itemView.findViewById(R.id.item_customer_contract_name_input);
        this.isApprove = itemView.findViewById(R.id.item_customer_contract_isApprove_input);
        this.expirationDate = itemView.findViewById(R.id.item_customer_contract_time_input);

        // Initialize View
        itemView.setOnClickListener(onClickListener);





    }

    public void setValue(CompensationResultListResponse response, View.OnClickListener listener) {
        this.expirationDateText.setText("처리 일자 : ");
        this.title.setText(response.getInsurance_name());
        this.isApprove.setText(response.getState());
        this.expirationDate.setText(response.getAccident_apply_date().substring(0, response.getAccident_apply_date().indexOf("T")));

    }
}