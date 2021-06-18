package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CustomerContractListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model.SalesConsultingListResponse;


public class ContractViewHolder extends RecyclerView.ViewHolder {



    // Associate
        // View

        private TextView title,isApprove,expirationDate,expirationDateText;
        // ETC
        private Activity activity;


    // Constructor
    public ContractViewHolder(View itemView, View.OnClickListener onClickListener, Activity activity) {
        super(itemView);

        // Associate
        this.activity=activity;

        // Associate View

        this.title = itemView.findViewById(R.id.item_customer_contract_name_input);
        this.isApprove = itemView.findViewById(R.id.item_customer_contract_isApprove_input);
        this.expirationDate = itemView.findViewById(R.id.item_customer_contract_time_input);
        this.expirationDateText=itemView.findViewById(R.id.item_customer_contract_time);
        // Initialize View
        itemView.setOnClickListener(onClickListener);





    }

    public void setValue(CustomerContractListResponse response, View.OnClickListener listener) {
        this.expirationDateText.setText("만료 일자 : ");
        this.itemView.setTag(getAdapterPosition());
        this.itemView.setOnClickListener(listener);
        this.title.setText(response.getInsurance_name());
        this.isApprove.setText(response.getApprove_state());
        this.expirationDate.setText(response.getExpiration_date().toString());

    }
}