package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CustomerContractListResponse;

import java.util.Vector;

public class ContractRecyclerAdapter extends RecyclerView.Adapter<ContractViewHolder> {

    // Associate
        // ETC
        private Activity activity;
        private Vector<CustomerContractListResponse> items;
        private View.OnClickListener onClickListener;

    // Constructor
    public ContractRecyclerAdapter(Activity activity, View.OnClickListener listener) {
        this.items = new Vector<>();
        this.activity=activity;
        this.onClickListener=listener;
    }




    @Override
    public ContractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_contract, parent, false);

        return new ContractViewHolder(view, v->this.startViewActivity((Long) v.getTag()), this.activity);
    }
    @Override
    public void onBindViewHolder(ContractViewHolder holder, int position) {
        holder.setValue(items.get(position),this.onClickListener);
    }
    @Override
    public int getItemCount() { return items.size(); }

    public void setItems(Vector<CustomerContractListResponse> items) { this.items=items; }
    private void startViewActivity(Long id) {


    }
}
