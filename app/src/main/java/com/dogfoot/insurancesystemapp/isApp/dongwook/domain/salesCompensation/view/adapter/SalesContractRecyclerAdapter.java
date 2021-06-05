package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationContractListResponse;

import java.util.Vector;

public class SalesContractRecyclerAdapter extends RecyclerView.Adapter<SalesContractViewHolder> {

    // Associate
        // ETC
        private Activity activity;
        private Vector<CompensationContractListResponse> items;
        private View.OnClickListener onClickListener;

    // Constructor
    public SalesContractRecyclerAdapter(Activity activity, View.OnClickListener listener) {
        this.items = new Vector<>();
        this.activity=activity;
        this.onClickListener=listener;
    }




    @Override
    public SalesContractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales_contract, parent, false);

        return new SalesContractViewHolder(view, v->this.startViewActivity((Long) v.getTag()), this.activity);
    }
    @Override
    public void onBindViewHolder(SalesContractViewHolder holder, int position) {
        holder.setValue(items.get(position),this.onClickListener);
    }
    @Override
    public int getItemCount() { return items.size(); }

    public void setItems(Vector<CompensationContractListResponse> items) { this.items=items; }
    private void startViewActivity(Long id) {


    }
}
