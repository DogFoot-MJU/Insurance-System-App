package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CompensationResultListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CustomerContractListResponse;

import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class ContractResultRecyclerAdapter extends RecyclerView.Adapter<ContractResultViewHolder> {

    // Associate
        // ETC
        private Activity activity;
        private Vector<CompensationResultListResponse> items;
        private View.OnClickListener onClickListener;

    // Constructor
    public ContractResultRecyclerAdapter(Activity activity, View.OnClickListener listener) {
        this.items = new Vector<>();
        this.activity=activity;
        this.onClickListener=listener;
    }




    @Override
    public ContractResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_contract, parent, false);

        return new ContractResultViewHolder(view, v->this.startViewActivity((Long) v.getTag()), this.activity);
    }



    @Override
    public void onBindViewHolder(ContractResultViewHolder holder, int position) {
        holder.setValue(items.get(position),this.onClickListener);
    }
    @Override
    public int getItemCount() { return items.size(); }

    public void setItems(Vector<CompensationResultListResponse> items) { this.items=items; }
    private void startViewActivity(Long id) {


    }
}
