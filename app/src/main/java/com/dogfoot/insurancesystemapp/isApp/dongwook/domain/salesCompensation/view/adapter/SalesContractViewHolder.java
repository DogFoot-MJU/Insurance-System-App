package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationContractListResponse;


public class SalesContractViewHolder extends RecyclerView.ViewHolder {



    // Associate
        // View

        private TextView title,isApprove,applyDate,userName;
        // ETC
        private Activity activity;


    // Constructor
    public SalesContractViewHolder(View itemView, View.OnClickListener onClickListener, Activity activity) {
        super(itemView);

        // Associate
        this.activity=activity;

        // Associate View

        this.title = itemView.findViewById(R.id.item_sales_contract_name_input);
        this.isApprove = itemView.findViewById(R.id.item_sales_contract_isApprove_input);
        this.applyDate = itemView.findViewById(R.id.item_sales_contract_time_input);
        this.userName = itemView.findViewById(R.id.item_sales_contract_author_input);

        // Initialize View
        itemView.setOnClickListener(onClickListener);





    }

    public void setValue(CompensationContractListResponse response, View.OnClickListener listener) {
        this.itemView.setTag(getAdapterPosition());
        this.itemView.setOnClickListener(listener);
        this.title.setText(response.getInsurance_name());
        this.isApprove.setText(response.getState());
        this.applyDate.setText(response.getAccident_apply_date());
        this.userName.setText(response.getUser_name());
    }
}